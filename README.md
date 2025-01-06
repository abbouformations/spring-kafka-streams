1) Step 1 : Start the Kafka server
  
  docker run -p 9092:9092 apache/kafka:3.9.0

2) Step 2 : Create the topics "input-ventes" and "casa-ventes" :

   execute the following commands : 

  ![image](https://github.com/user-attachments/assets/faf7ed11-626f-45a4-b543-a47012b13b80)

  
sh kafka-topics.sh --bootstrap-server localhost:9092 --create --topic input-ventes --partitions 1 --replication-factor 1

sh kafka-topics.sh --bootstrap-server localhost:9092 --create --topic casa-ventes --partitions 1 --replication-factor 1

3) Step 3 : Launch the main method of the MainApplication class.
   
4) Step 4: Send some messages :

   http://localhost:8081/send/111/casa/1000
   
   http://localhost:8081/send/111/casa/4000

   http://localhost:8081/send/999/casa/3000

   http://localhost:8081/send/999/rabat/1000
   
5) Step 5 : Check the result

   http://localhost:8081/ventes/customer/999

   ![image](https://github.com/user-attachments/assets/9b12f447-b531-40e5-ab9e-f0f7ef0661f5)


   


   
