###This Utility is combination of GZip compression and HTTP caching could be used as filter to improve any web application performance

#####Usage: GZip Comprssion: add below code in web.xml to apply GZip compression

	<filter>
		<filter-name>GZIPFilter</filter-name>
		<filter-class>com.opcat.gzip.GZIPFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>GZIPFilter</filter-name>
		<url-pattern>*</url-pattern>
	</filter-mapping> 

#####Caching add the below code in web.xml to apply caching filter

	<filter>
		<filter-name>jsCache</filter-name>
		<filter-class>com.opcat.caching.CacheFilter</filter-class>
		<init-param>
			<param-name>private</param-name>
			<param-value>false</param-value>
		</init-param>
		<init-param>
			<param-name>expirationTime</param-name>
			<!-- Change this to add the expiry time for re-validating the files -->
			<param-value>0</param-value> 
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>jsCache</filter-name>
		<url-pattern>*</url-pattern>
	</filter-mapping>


