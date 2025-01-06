1) Step 1 : Start the Kafka server
  
  docker run -p 9092:9092 apache/kafka:3.9.0

2) Step 2 : Create the topics "input-ventes" and "casa-ventes" :

   execute the following commands : 

  ![image](https://github.com/user-attachments/assets/faf7ed11-626f-45a4-b543-a47012b13b80)

  
sh kafka-topics.sh --bootstrap-server localhost:9092 --create --topic input-ventes --partitions 1 --replication-factor 1

sh kafka-topics.sh --bootstrap-server localhost:9092 --create --topic casa-ventes --partitions 1 --replication-factor 1

   

   


   
