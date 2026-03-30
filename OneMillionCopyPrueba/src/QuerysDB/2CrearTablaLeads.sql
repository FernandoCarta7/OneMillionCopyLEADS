USE OneMillionCopyPruebaDB;

CREATE TABLE leads (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(50),
    fuente VARCHAR(50) NOT NULL,
    producto_interes VARCHAR(255),
    presupuesto DECIMAL(10,2),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
