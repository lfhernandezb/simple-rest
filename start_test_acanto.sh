source setenv_test_acanto.sh
java -javaagent:elastic-apm-agent.jar -Delastic.apm.service_name=simple-rest -Delastic.apm.application_packages=org.example -Delastic.apm.server_url=http://172.31.218.37:8200 -Delastic.apm.use_path_as_transaction_name=true -jar build/libs/simple-rest-0.0.1-SNAPSHOT.jar
#java -javaagent:elastic-apm-agent-1.52.1.jar -Delastic.apm.service_name=simple-rest -Delastic.apm.application_packages=org.example -Delastic.apm.server_url=http://localhost:8200 -jar build/libs/simple-rest-0.0.1-SNAPSHOT.jar
