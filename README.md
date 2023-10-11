# Spring Boot RabbitMQ Publisher and Receiver Example

This project is a simple example of how to create a publisher and receiver for RabbitMQ using Spring Boot. RabbitMQ is a widely-used message broker that enables applications to communicate with each other in a decoupled manner. In this project, we will demonstrate how to send and receive messages via RabbitMQ in a Spring Boot application.

## Prerequisites

Before you begin, ensure you have the following prerequisites:

1. Java Development Kit (JDK) installed on your machine (preferably Java 8 or later).
2. [Apache Maven](https://maven.apache.org/download.cgi) installed.
3. RabbitMQ Server running locally or accessible via the network.
    - You can install RabbitMQ locally by following the instructions [here](https://www.rabbitmq.com/download.html).
    - Make sure RabbitMQ is up and running.

## Project Structure

The project structure is simple, consisting of two modules: `publisher` and `receiver`. Both modules are Spring Boot applications.

- `publisher`: A Spring Boot application that sends messages to RabbitMQ.
- `receiver`: A Spring Boot application that listens for messages from RabbitMQ.

## Configuration
Both the Publisher and Receiver applications are configured to connect to RabbitMQ using default settings. You can customize the RabbitMQ connection settings in the application.properties files of each module.

## License
This project is open-source and available under the MIT License. You are free to use, modify, and distribute it as needed.

