# CMMS-Microservices

*A simplified Computerized Maintenance Management System (CMMS) built with Spring Boot microservices and Apache Kafka to demonstrate event-driven architecture, reliable asynchronous messaging, and modern backend engineering practices.*

---

# Overview

CMMS-Microservices is a portfolio project that demonstrates how a **Computerized Maintenance Management System (CMMS)** can be implemented using an **event-driven microservice architecture**.

The system models the lifecycle of preventive maintenance for industrial equipment. Rather than focusing solely on CRUD operations, it implements a realistic business workflow where independent microservices collaborate by exchanging asynchronous events.

Each microservice owns its business domain and persists its own data. Services communicate through Apache Kafka using integration events, allowing them to remain loosely coupled while maintaining a clear separation of responsibilities.

To guarantee reliable event publication, every service that produces events implements the **Transactional Outbox Pattern**. Business data and integration events are committed within the same database transaction, and an asynchronous Outbox Publisher subsequently delivers events to Kafka. This approach prevents inconsistencies between the database state and published messages.

Although simplified for demonstration purposes, the project incorporates architectural patterns commonly used in enterprise backend systems.

---

# Business Workflow

The project simulates the execution of a preventive maintenance process from scheduling maintenance tasks to updating equipment status.

## 1. System Initialization

When the application starts, Flyway initializes the database with:

* Equipment information
* Preventive maintenance definitions
* Maintenance tasks assigned to equipment

The Maintenance Service continuously checks for maintenance tasks that are ready for execution.

---

## 2. Work Order Creation

When a maintenance task becomes ready:

* The Maintenance Service creates a work order request.
* The business transaction and integration event are stored atomically using the Transactional Outbox Pattern.
* The Outbox Publisher publishes a **MaintenanceTaskReadyEvent** to Apache Kafka.
* The WorkOrder Service consumes the event and creates a new work order.

---

## 3. Technician Assignment

A manager assigns a technician through a REST API.

After the assignment is completed:

* The WorkOrder Service updates the work order status.
* A **WorkOrderAssignedEvent** is stored in the Outbox table.
* The Outbox Publisher publishes the event to Kafka.

---

## 4. Notification

The Notification Service consumes the **WorkOrderAssignedEvent**.

For demonstration purposes, the service simulates notification delivery by logging email and message notifications.

---

## 5. Maintenance Completion

After performing the maintenance, the technician submits maintenance results through a REST API.

The WorkOrder Service:

* Marks the work order as completed.
* Stores a **MaintenanceCompletedEvent** in its Outbox table.
* Publishes the event asynchronously to Kafka.

---

## 6. Equipment Status Update

The Equipment Service consumes the **MaintenanceCompletedEvent**.

Based on the maintenance outcome, it updates the equipment status and maintenance history, completing the maintenance lifecycle.

---

# Architecture

The application follows an event-driven microservice architecture where each service owns its business logic and database.

Current services include:

* Equipment Service
* Maintenance Service
* WorkOrder Service
* Notification Service

Communication between services is performed through:

* REST APIs for user-initiated commands
* Apache Kafka for asynchronous business events

Every event-producing service implements the Transactional Outbox Pattern to ensure reliable event publication.

---

# Event Flow

| Producer            | Event                     | Consumer             |
| ------------------- | ------------------------- | -------------------- |
| Maintenance Service | MaintenanceTaskReadyEvent | WorkOrder Service    |
| WorkOrder Service   | WorkOrderAssignedEvent    | Notification Service |
| WorkOrder Service   | MaintenanceCompletedEvent | Equipment Service    |

---

# Service Responsibilities

## Equipment Service

Responsible for:

* Equipment management
* Equipment status updates
* Equipment maintenance history

---

## Maintenance Service

Responsible for:

* Preventive maintenance definitions
* Assigned maintenance tasks
* Detecting ready maintenance tasks
* Publishing MaintenanceTaskReadyEvent

---

## WorkOrder Service

Responsible for:

* Work order creation
* Technician assignment
* Work order lifecycle
* Publishing integration events

---

## Notification Service

Responsible for:

* Consuming notification events
* Simulating email notifications
* Simulating message notifications

---

# Reliability

Every service that publishes integration events uses the **Transactional Outbox Pattern**.

Instead of publishing directly to Kafka within a business transaction, the service:

1. Executes the business transaction.
2. Stores the integration event in an Outbox table.
3. Commits the transaction.
4. An asynchronous Outbox Publisher publishes the event to Kafka.

This approach guarantees that business data and integration events remain consistent while avoiding distributed transactions.

---

# Technology Stack

Backend
- Java 21
- Spring Boot 3.x

Messaging
- Apache Kafka

Database
- PostgreSQL
- Flyway

Testing
- JUnit 5
- Mockito

DevOps
- Docker
- GitHub Actions

---
## Testing Strategy

This project demonstrates several testing approaches:

- Unit tests for business rules
- Integration tests for REST APIs
- Transactional Outbox tests
- Kafka publisher tests
- Idempotent event processing tests

---  
# Running the Project

The entire platform can be started with a single Docker Compose command. No local installation of Java, PostgreSQL, or Kafka is required.
## 1. Clone the Repository

```bash
git clone https://github.com/ElaheYousefi/CMMS-Microservices.git
cd CMMS-Microservices
```

## 2. Prerequisites

- Docker Desktop (or Docker Engine with Docker Compose)

## 3. Start the Application

```bash
cd docker
docker compose up
```

Or run in the background:

```bash
docker compose up -d
```

## 4. What Happens

Running `docker compose up` will:

- Start PostgreSQL
- Start Apache Kafka
- Start all Spring Boot microservices
- Connect the services to PostgreSQL and Kafka
- Expose the REST APIs for testing

## 5. Available Services

- equipment-service
- workorder-service
- notification-service
- maintenance-service
- PostgreSQL
- Apache Kafka
---

# Future Improvements

* OpenAPI / Swagger documentation
* Authentication and Authorization
* Monitoring and Observability
* Kubernetes deployment
