# Orderflow: Order Processing & Delivery Platform

## Starting instructions

Run the following command from root project directory - 

- To bring up the postgres db : `docker compose -f orderflow-compose.yml up`
- To start the application : `./gradlew bootRun`

### Navigating the api

Following endpoints are available -

| Endpoint | Purpose |
| --- | --- |
| `GET /api/v1/orders`| Get all orders |
| `GET /api/v1/orders/{id}` | Fetch a single order |
| `POST /api/v1/orders` | Create an order |
| `GET /api/v1/orderItems`| Get all orderItems |
| `GET /api/v1/orderItems/{id}` | Fetch a single orderItem/product |
| `POST /api/v1/orderItems` | Create an orderItem |