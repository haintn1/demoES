package digi.ecomm.commercesearch.search.populator;

import digi.ecomm.commercesearch.repository.EsPromotedItemRepository;
import digi.ecomm.commercesearch.search.data.SearchQueryPageableData;
import digi.ecomm.entity.commercesearch.advance.EsPromotedItem;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchSourceBuilderPromotedItemPopulator implements Populator<SearchQueryPageableData, SearchSourceBuilder> {
    private static final float MAX_PROMOTED_SCORE = 1000000;
    private static final float PROMOTED_SCORE_STEP = 5000;

    private final EsPromotedItemRepository promotedItemRepository;

    public SearchSourceBuilderPromotedItemPopulator(final EsPromotedItemRepository promotedItemRepository) {
        this.promotedItemRepository = promotedItemRepository;
    }

    @Override
    public void populate(final SearchQueryPageableData source, final SearchSourceBuilder target)
            throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);

        final List<EsPromotedItem> promotedItems =
                promotedItemRepository.findByAdvancedSearchConfig(source.getContext().getAdvancedSearchConfig());
        if (CollectionUtils.isNotEmpty(promotedItems)) {
            final String inlineScript = "params[doc['id'].value] ?: 0";
            final Map<String, Object> params = new HashMap<>();
            float score = MAX_PROMOTED_SCORE;
            for (EsPromotedItem promotedItem : promotedItems) {
                params.put(String.valueOf(promotedItem.getItemId()), score);
                score -= PROMOTED_SCORE_STEP;
                if (score < 0) {
                    score = 0;
                }
            }
            final Script script = new Script(ScriptType.INLINE, Script.DEFAULT_SCRIPT_LANG, inlineScript, params);
            target.sort(SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER).order(SortOrder.DESC));
        }
    }
}
