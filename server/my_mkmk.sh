gradle clean
gradle war

if [ -f app/build/libs/bricolageV2.war ]; then
	rm -rf /z/bin/w_tomee/apache-tomee-8.0.1-plus/webapps/bricolageV2.war
	cp app/build/libs/bricolageV2.war /z/bin/w_tomee/apache-tomee-8.0.1-plus/webapps
	printf "OK copie done \n"
else
	printf "KO \n"
fi

