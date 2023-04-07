### **How to getting started with B2B solution**

1/ **Datasoure config (`application.yml` files):**<br/>
`spring.datasource.url=jdbc:mysql://localhost:3306/b2b_platform`<br/>
`spring.datasource.username=root`<br/>
`spring.datasource.password=root`<br/>
=> Change these values to yours own<br/>

2/ **Compile source**<br/>
`cd b2bcore`<br/>
`mvn clean install`<br/>

3/ **Start core service**<br/>
`cd corecustomapi`<br/>
* Windows:<br/>
`debug.bat`<br/>
* Linux:<br/>
`sh debug.sh`<br/>
* API document page http://localhost:8080/api/swagger-ui/index.html<br/>

4/ **Start search service**<br/>
`cd searchcustomapi`<br/>
* Windows:<br/>
`debug.bat`<br/>
* Linux:<br/>
`sh debug.sh`<br/>
* API document page http://localhost:8082/api/swagger-ui/index.html<br/>


### ** Code quality assurance **
`cd b2bcore`<br/>
* Windows:<br/>
`scan-code.bat`<br/>
* Linux:<br/>
`sh scan-code.sh`<br/>