# 🚀 Spring Boot Microservices on AWS

Este repositório contém o projeto desenvolvido durante o curso **"Crie a infraestrutura de microsserviços Spring Boot em cluster com AWS CDK, usando ECS, SNS, SQS, RDS, DynamoDB e S3."**

O objetivo do projeto é aplicar conceitos de **microsserviços com Spring Boot** e integrá-los aos principais serviços da **AWS**, explorando práticas modernas de **cloud computing** e **infraestrutura como código (IaC)**.

---

## 📌 Tecnologias utilizadas
- **Java 17**
- **Spring Boot 3.5.5**
- **AWS CDK**
- **ECS (Elastic Container Service)**
- **SNS (Simple Notification Service)**
- **SQS (Simple Queue Service)**
- **RDS (Relational Database Service)**
- **DynamoDB**
- **S3 (Simple Storage Service)**

---

## 🎯 Objetivos do projeto
- Estruturar uma arquitetura baseada em **microsserviços**.
- Criar infraestrutura em **cluster** usando **AWS CDK**.
- Integrar os microsserviços com **filas, tópicos, bancos relacionais e NoSQL**.
- Armazenar arquivos no **Amazon S3**.
- Demonstrar boas práticas de **deploy e escalabilidade** na nuvem.

---

## 📂 Estrutura inicial do projeto
- `spring-aws-microservices/product-service` → Microservico com crud de produtos, e responsável por publicar eventos
- `spring-aws-microservices/inventory-service` → Microservico responsável por consumir os eventos de uma fila SQS, e persistir no DynamoDB

---

## 🚧 Status
Em desenvolvimento ⚡

---

## 📬 Contato
📌 Desenvolvido por [Viniccius Neves](https://www.linkedin.com/in/viniccius-neves)  
