MICROSERVICIOS CREADOS: 
1) SERVICE-CLIENTE 
2) SERVICE_CATALOGO_PRODUCTO
3) SERVICE_CATALOGO_SERVICE
4) SERVICE-INVENTARIO
5) SERVICE_CARRITO_DE_COMPRAS
6) API_GATEWAY

APLICACIONES REQUERIDAS PARA SU CREACION:
-FRAMEWORK: SPRING BOOT 
-GESTOR DE DEPENDENCIAS: MAVEN
-BASE DE DATOS: MYSQL
-DEPENDENCIAS DE (CLIENTE-PRODUCTO-SERVICIOS): LOMBOK, SPRING WEB, VALIDATION, SPRING DATA JPA, MySQL Driver
-DEPENDENCIAS DE (INVENTARIO-CARRITO): VALIDATION, SPRING DATA JPA, SPRING REACTIVE WEB, MySQL Driver, LOMBOK
-DEPENDENCIAS DE (API-GATEWAY): GATEWAY

BASES DE DATOS: 
1) SERVICE-CLIENTE: DB_CLIENTES
2) SERVICE-CATALOGO-PRODUCTO: DB_CATALOGO_PRODUCTO
3) SERVICE-CATALOGO-SERVICE: DB_SERVICIOS
4) SERVICE-INVENTARIO: DB_INVENTARIO
5) SERVICE-CARRITO-DE-COMPRAS: DB_CARRITO

PUERTOS:
1) SERVICE-CLIENTE: 8084
2) SERVICE-CATALOGO-PRODUCTO : 8085
3) SERVICE-CATALOGO-SERVICE: 8086
4) SERVICE-INVENTARIO: 8087
5) SERVICE-CARRITO-DE-COMPRAS: 8088
6) API_GATEWAY: 9090

EJECUCION DE PETICIONES: 
CLIENTE:
http://localhost:9090/api/v1/clientes => GET
http://localhost:9090/api/v1/clientes/{Id} => GET
http://localhost:9090/api/v1/clientes => POST
http://localhost:9090/api/v1/clientes/{Id} => PUT
http://localhost:9090/api/v1/clientes/{Id} => DELETE

http://localhost:9090/api/v1/direcciones => GET
http://localhost:9090/api/v1/direcciones => POST
http://localhost:9090/api/v1/direcciones/{Id} => PUT
http://localhost:9090/api/v1/direcciones/{Id} => DELETE

PRODUCTO:
http://localhost:9090/api/v1/categorías => GET
http://localhost:9090/api/v1/categorías/{Id} => GET
http://localhost:9090/api/v1/categorías/conteo => GET
http://localhost:9090/api/v1/categorías => POST
http://localhost:9090/api/v1/categorías/{Id} => PUT
http://localhost:9090/api/v1/categorías/{Id} => DELETE

http://localhost:9090/api/v1/producto => GET
http://localhost:9090/api/v1/producto/{Id} => GET
http://localhost:9090/api/v1/producto => POST
http://localhost:9090/api/v1/producto/{Id} => PUT
http://localhost:9090/api/v1/producto/{Id} => DELETE

SERVICIO: 
http://localhost:9090/api/v1/servicios => GET
http://localhost:9090/api/v1/servicios/{Id} => GET
http://localhost:9090/api/v1/servicios => POST
http://localhost:9090/api/v1/servicios/{Id} => PUT
http://localhost:9090/api/v1/servicios/{Id} => DELETE

INVENTARIO: 
http://localhost:9090/api/v1/stock => GET
http://localhost:9090/api/v1/stock/{Id} => GET
http://localhost:9090/api/v1/stock => POST
http://localhost:9090/api/v1/stock/{Id} => PUT
http://localhost:9090/api/v1/stock/{Id} => DELETE

http://localhost:9090/api/v1/movimientos => GET
http://localhost:9090/api/v1/movimientos/{Id} => GET
http://localhost:9090/api/v1/movimientos/conteo/{stockId} => GET
http://localhost:9090/api/v1/movimientos => POST
http://localhost:9090/api/v1/movimientos/{Id} => PUT
http://localhost:9090/api/v1/movimientos/{Id} => DELETE

CARRITO DE COMPRAS:
http://localhost:9090/api/v1/carrito => GET
http://localhost:9090/api/v1/carrito/totalP => GET
http://localhost:9090/api/v1/carrito => POST
http://localhost:9090/api/v1/carrito/{Id} => PUT
http://localhost:9090/api/v1/carrito/{Id} => DELETE

DATOS DE PRUEBA:
CLIENTE:
{
    "rut": "19123456-7",
    "nombre": "Gabriel",
    "apellido": "González",
    "email": "gabriel@correo.com",
    "fechaCreado": "2026-05-13",
    "direccionCliente": {
        "calle": "Avenida Los Pajaritos",
        "numeroCasa": 1234,
        "ciudad": "Santiago",
        "comuna": "Maipú",
        "region": "Metropolitana"
    }
}
CLIENTE(DIRECCIÓN)
{
  "calle": "Avenida Providencia",
  "numeroCasa": 1024,
  "ciudad": "Santiago",
  "comuna": "Providencia",
  "region": "Metropolitana",
  "cliente": {
    "id": 1
  }
}
PRODUCTO(CATEGORIA)
{
  "nombre": "Papelería"
}
PRODUCTO: 
{
  "codigo": "PROD-001",
  "nombre": "Cuaderno Universitario",
  "precio_base": 5500,
  "peso_kilos": 5.5,
  "dimensiones": 30.5,
  "categoria": {
    "id": 1
  }
}
SERVICIO:
{
  "codigo": "SERV-001",
  "tipo": "Encuadernación",
  "precio_desde": 75000,
  "tiempo_estimado_entrega": "2026-05-20",
  "descripcion": "Pagina web de mi empresa de construcción Constructora WEN"
}
STOCK:
{
  "cantidad": 100,
  "minimoParaReposicion": 10,
  "productoId": 1
}
MOVIMIENTO:
{
  "tipo": true,
  "cantidad": 15,
  "motivo": "Ingreso de nueva mercadería al sistema",
  "fecha": "2026-05-15",
  "stock": {
    "id": 1
  }
}
CARRITO:
{
  "referenciaIdProducto": "2",
  "cantidad": 2
}
