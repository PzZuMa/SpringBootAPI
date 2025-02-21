<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <h1>Documentación de la API</h1>
    <h2>Descripción</h2>
    <p>Esta API proporciona acceso a información y funcionalidades relacionadas con:</p>
    <ul>
        <li><strong>Hoteles:</strong> Consulta, creación, actualización y eliminación de hoteles, así como reservas asociadas.</li>
        <li><strong>Vuelos:</strong> Consulta, creación, actualización y eliminación de vuelos, así como reservas asociadas.</li>
        <li><strong>Puntos de Interés (POIs):</strong> Consulta, creación, actualización y eliminación de puntos de interés.</li>
    </ul>
    <p>La API está protegida por un sistema de validación basado en tokens para garantizar la seguridad.</p>
    <h2>Recursos Disponibles</h2>
    <ul>
        <li>Hoteles - Gestión de hoteles y reservas asociadas.</li>
        <li>Vuelos - Gestión de vuelos y reservas asociadas.</li>
        <li>Puntos de Interés (POIs) - Gestión de puntos turísticos.</li>
    </ul>
    <h2>Endpoints</h2>
    <h3>1. Hoteles</h3>
    <h4>1.1 Listar todos los hoteles</h4>
    <p><strong>Método:</strong> GET</p>
    <p><strong>URL:</strong> /api/hoteles</p>
    <p><strong>Descripción:</strong> Devuelve una lista con todos los hoteles disponibles.</p>
    <pre><code>
    [
        {
            "id": "1",
            "nombre": "Hotel Plaza Mayor",
            "ciudad": "Madrid",
            "estrellas": 4,
            "precioPorNoche": 120.5
        },
        {
            "id": "2",
            "nombre": "Hotel Sol",
            "ciudad": "Barcelona",
            "estrellas": 5,
            "precioPorNoche": 200.0
        }
    ]
    </code></pre>
    <h4>1.2 Obtener un hotel por su ID</h4>
    <p><strong>Método:</strong> GET</p>
    <p><strong>URL:</strong> /api/hoteles/{id}</p>
    <p><strong>Descripción:</strong> Obtén la información detalla de un hotel de la base de datos mediante su ID.</p>
    <pre><code>
    {
        "_id":"67b386a55d15acb64c088666"
        "nombre": "Hnos Nicolau S.L. Hotel",
        "ciudad": "Málaga",
        "direccion":"Glorieta Goyo Sanabria 36",
        "telefono":"+34886 731 781",
        "email":"alcoleaciro@distribuciones.net"
        "estrellas": 5,
        "precioPorNoche": 121.77
    }
    </code></pre>
    <h4>1.3 Crear un nuevo hotel</h4>
    <p><strong>Método:</strong> POST</p>
    <p><strong>URL:</strong> /api/hoteles</p>
    <p><strong>Descripción:</strong> Crea un nuevo hotel en la base de datos.</p>
    <pre><code>
    {
        "nombre": "Hotel Costa del Sol",
        "ciudad": "Málaga",
        "estrellas": 3,
        "precioPorNoche": 90.0
    }
    </code></pre>
    <h4>1.4 Actualizar un hotel existente</h4>
    <p><strong>Método:</strong> PUT</p>
    <p><strong>URL:</strong> /api/hoteles/{id}</p>
    <p><strong>Descripción:</strong> Actualiza los detalles de un hotel existente.</p>
    <pre><code>
    {
        "nombre": "Hotel Costa del Sol Renovado",
        "precioPorNoche": 95.0
    }
    </code></pre>
    <h4>1.5 Eliminar un hotel</h4>
    <p><strong>Método:</strong> DELETE</p>
    <p><strong>URL:</strong> /api/hoteles/{id}</p>
    <p><strong>Descripción:</strong> Elimina un hotel por su ID.</p>
    <pre><code>
    {
        "mensaje": "Hotel eliminado exitosamente"
    }
    </code></pre>
    <h4>1.6 Reservar un hotel</h4>
    <p><strong>Método:</strong> POST</p>
    <p><strong>URL:</strong> /api/hoteles/reserva/{id}</p>
    <p><strong>Descripción:</strong> Realiza una reserva para un hotel específico.</p>
    <pre><code>
    {
        "hotel": {
            "id": "1",
            "nombre": "Hotel Plaza Mayor"
        },
        "usuario": {
            "id": "101",
            "nombre": "Carlos García"
        }
    }
    </code></pre>
    <h2>Autenticación</h2>
    <p>La API utiliza tokens para validar las solicitudes protegidas. El token debe enviarse como parámetro en las solicitudes que lo requieran.</p>
    <h2>Ejecución</h2>
    <h3>Requisitos Previos</h3>
    <ul>
        <li>JDK instalado (Java Development Kit).</li>
        <li>Maven instalado para gestionar dependencias.</li>
        <li>Base de datos configurada (por ejemplo, MySQL).</li>
    </ul>
    <h3>Configuración</h3>
        <p>Configura el archivo <code>application.properties</code> en <code>src/main/resources</code>:</p>
            <pre><code>
    spring.datasource.url=jdbc:mysql://localhost:3306/api_db
    spring.datasource.username=root
    spring.datasource.password=1234
    spring.jpa.hibernate.ddl-auto=update
            </code></pre>
    <h3>Ejecución</h3>
        <p>Clona el repositorio y compila el proyecto con Maven:</p>
            <pre><code>
    mvn clean install
            </code></pre>
        <p>Ejecuta la aplicación:</p>
            <pre><code>
    mvn spring-boot:run
            </code></pre>
        <p>Accede a la API en <a href="http://localhost:8080/api">http://localhost:8080/api</a>.</p>
    <h2>Tecnologías Utilizadas</h2>
    <ul>
        <li>Java (Spring Boot)</li>
        <li>MongoDB (Base de datos)</li>
        <li>Thymeleaf (para vistas HTML)</li>
        <li>Maven (gestión de dependencias)</li>
    </ul>
</body>
</html>

