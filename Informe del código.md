# Escuela de Formación de Tecnólogos

## _Proyecto de Programación Orientada a Objetos_

**Nombre:** _Mateo Aldair Torres Lara_

El proyecto retrata un sistema de generación, gestión y manejo de reservas para una organización que se encarga de la reservas de canchas sintétitcas

# Liniamientos propuestos para este proyecto
## Usuarios 
Se determinaron 3 usuarios:
### Administrador: 
- Responsable de realizar un CRUD (Crear, Leer, Actualizar y Borrar) para usuarios, canchas y reservas. Sin embargo, en el caso de las reservas, solo se permite su modificación.
- El administrador tendrá acceso a una tabla donde podrá seleccionar y manipular usuarios, canchas y reservas.
### Dueño o Encargado de la Cancha: 
- Responsable de brindar servicio al cliente y abrir las canchas según las reservas agendadas.
- Podrá visualizar todas las reservas realizadas.
### Clientes o Jugadores:
- Usuarios que utilizarán el sistema para reservar canchas, eligiendo la cancha, la fecha y el horario deseado.
- Pueden registrarse, iniciar sesión y realizar reservas.
## Control de errores 
Se implementaron mecanismos de control de errores en todos los formularios y forms del sistema para garantizar que los usuarios no puedan generar errores que comprometan la integridad del sistema. Se realizaron diversas comprobaciones para mantener una base de datos y un sistema robusto.
## Diseño de las interfaces
El diseño de cada interfaz se realizó teniendo en cuenta la funcionalidad del sistema y su relación con los usuarios. Se procuró que las interfaces fueran intuitivas y fáciles de usar.
## Conexión con la base de datos
Se estableció una conexión óptima con MongoDB para asegurar el manejo correcto de los datos dentro del sistema, permitiendo un rendimiento adecuado y fiable.

# Presentación del Codigo y de las diferentes interfaces
1. ## Clases
Para el instanciamiento de los diferentes objetos que participan en este sistema, se crearon 4 clases para mejorar la recopilación y manejo de datos, brindados del usuario o llamados de la base de datos.

- ### Usuarios:
En la clase Usuario se crearon los atributos: nombre, apellido, email, telefono, clave, cedula, confirmación de clave, clave encriptada, valor a cambiar, Campo a cambiar, día de Nacimiento, mes de Nacimiento, año de Nacimiento.

![image](https://github.com/user-attachments/assets/710f2ba4-9c44-48e7-ae21-788ed3bf9e2f)
Se determinaron sus contructores

![image](https://github.com/user-attachments/assets/b662680b-eebf-48e9-9f06-2c36c5cb0fc9)
Getters y Setters

![image](https://github.com/user-attachments/assets/b829bfb4-2bb8-4361-8c2c-8bf03d8787b5)

#### Métodos personalizados
1. ##### Verificación de Correo:
Método booleano que verifica que el correo ingresado por el Usuario al registrarse, cumpla con el formato correcto.
![image](https://github.com/user-attachments/assets/40b4b3de-9cbb-4473-a500-590681e438ec)

2. ##### Verificación de un correo ya existente:
Método booleano que se encarga de verificar que el correo ingresado por el usuario al registrarse, no se encuentre registrado.
![image](https://github.com/user-attachments/assets/cf2fa8bd-8e9b-45af-a850-116c4acfbb1e)

3. ##### Verificación de Correo con parámetro:
Método booleano que verifica que el correo ingresado por el Administrador al editar usuario, cumpla con el formato correcto.
![image](https://github.com/user-attachments/assets/702b97e1-211a-47b6-8b68-035b584438e3)

4. ##### Verificación de patrón de número de teléfono
Métodos con y sin parametros, para la edición y registro de un usuario respectivamente. 
![image](https://github.com/user-attachments/assets/cb540cfe-f18b-4544-a51e-f230062a108a)

5. ##### Verificación de un teléfono ya existente:
Método booleano que se encarga de verificar que el teléfono ingresado por el usuario al registrarse, no se encuentre registrado.
![image](https://github.com/user-attachments/assets/3f85ef84-f785-4aa7-a2b4-4d84785107ec)

6. ##### Verificación de Cédula:
Método booleano que verifica que la cédula ingresada por el Usuario al registrarse, cumpla con el formato correcto.
![image](https://github.com/user-attachments/assets/15d08d67-b1a1-4b9a-b599-3db1f1284b2b)

7. ##### Encriptación de la contraseña ingresada:
Métodos para encriptar las contraseñas ingresadas por el usuario al registrarse y al editar sus datos.
![image](https://github.com/user-attachments/assets/18adb7be-4867-498c-b792-78d2736e751d)




8. 

-

2. ## Forms
