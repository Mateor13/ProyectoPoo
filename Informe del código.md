# Escuela de Formación de Tecnólogos

## _Proyecto de Programación Orientada a Objetos_

**Nombre:** _Mateo Aldair Torres Lara_

El proyecto retrata un sistema de generación, gestión y manejo de reservas para una organización que se encarga de la reservas de canchas sintétitcas

# Lineamientos propuestos para este proyecto
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
1. #### Verificación de Correo:
Método booleano que verifica que el correo ingresado por el Usuario al registrarse, cumpla con el formato correcto.
![image](https://github.com/user-attachments/assets/40b4b3de-9cbb-4473-a500-590681e438ec)

2. #### Verificación de un correo ya existente:
Método booleano que se encarga de verificar que el correo ingresado por el usuario al registrarse, no se encuentre registrado.
![image](https://github.com/user-attachments/assets/cf2fa8bd-8e9b-45af-a850-116c4acfbb1e)

3. #### Verificación de Correo con parámetro:
Método booleano que verifica que el correo ingresado por el Administrador al editar usuario, cumpla con el formato correcto.
![image](https://github.com/user-attachments/assets/702b97e1-211a-47b6-8b68-035b584438e3)

4. #### Verificación de patrón de número de teléfono
Métodos con y sin parametros, para la edición y registro de un usuario respectivamente. 
![image](https://github.com/user-attachments/assets/cb540cfe-f18b-4544-a51e-f230062a108a)

5. #### Verificación de un teléfono ya existente:
Método booleano que se encarga de verificar que el teléfono ingresado por el usuario al registrarse, no se encuentre registrado.
![image](https://github.com/user-attachments/assets/3f85ef84-f785-4aa7-a2b4-4d84785107ec)

6. #### Verificación de Cédula:
Método booleano que verifica que la cédula ingresada por el Usuario al registrarse, cumpla con el formato correcto.
![image](https://github.com/user-attachments/assets/15d08d67-b1a1-4b9a-b599-3db1f1284b2b)

7. #### Encriptación de la contraseña ingresada:
Métodos para encriptar las contraseñas ingresadas por el usuario al registrarse y al editar sus datos.
![image](https://github.com/user-attachments/assets/18adb7be-4867-498c-b792-78d2736e751d)
![image](https://github.com/user-attachments/assets/3ce4baa5-6330-47b0-b467-1879ec1a7999)
![image](https://github.com/user-attachments/assets/c6f4bf2e-4d5b-43ca-8295-763a2556459d)

8. #### Mostrar los datos de la Base de Datos en la tabla de gestión de Usuarios
Se muestran los datos en una tabla ya establecida.
![image](https://github.com/user-attachments/assets/2824a377-3588-4820-99fc-7873d98bec00)

9. #### Eliminar un registro directamente de la tabla de gestión de Usuarios
Se selecciona un registro y se elimina de la tabla, y de la Base de Datos.
![image](https://github.com/user-attachments/assets/5717073d-d713-487a-9b30-2717016cca96)

10. #### Actualizar los datos de los usuarios seleccionados en gestión de Usuarios
Se seleccionara un registro de la tabla y se guardará la cédula, para poder editar el usuario seleccionado en el form editarJugadores.
![image](https://github.com/user-attachments/assets/e89dba50-dabb-4852-a8bf-0b3896972a7f)

11. #### Determinar que se haya seleccionado un registro de la tabla de Usuarios
Se determina un método booleano que verifica que el usuario haya seleccionado una fila de la tabla.
![image](https://github.com/user-attachments/assets/1725cf31-3cfd-4e07-ab45-a4f48d0119d3)

12. #### Verificar la clave ingresada por el Usuario
Método booleano que verifica si la clave ingresada por el Usuario es igual a la guardada en la Base de Datos.
![image](https://github.com/user-attachments/assets/86753fd8-f70a-4fd0-b742-857c85a3a7d1)

- ### Canchas
En la clase Canchas se crearon los siguientes atributos: numero, nombre, ubicacion, numeroJugadores, btn, input;
![image](https://github.com/user-attachments/assets/7ee8b053-c25d-4f3f-87cd-d3df472a3fa4)
Getters y Setters
![image](https://github.com/user-attachments/assets/ab3735d3-32fc-4d84-b534-e7347dd38d60)

#### Métodos personalizados
1. #### Verificación patrón de númeracion de cancha
Método booleano que verifica que el número de cancha ingresada por el Administrador al registrar una cancha, cumpla con el formato correcto.
![image](https://github.com/user-attachments/assets/68352120-72df-4776-a21f-ff9938b4b0e6)

2. #### Verificación de una cancha ya existente:
Método booleano que se encarga de verificar que la cancha ingresada por el administrador al registrar una cancha, no se encuentre registrada. 
![image](https://github.com/user-attachments/assets/863fa97c-4053-445b-8954-b4dee8998ea7)

3. #### Determinar que se haya seleccionado un registro de la tabla de Canchas
Se determina un método booleano que verifica que el administrador haya seleccionado una fila de la tabla.
![image](https://github.com/user-attachments/assets/e93412e3-3997-4595-853b-2aa65e6e2282)

4. #### Mostrar los datos de la Base de Datos en la tabla de gestión de Canchas
Se muestran los datos en una tabla ya establecida para los Usuarios clientes.
![image](https://github.com/user-attachments/assets/9905eebe-5fcd-4bf9-821c-693daf45348f)
![image](https://github.com/user-attachments/assets/2b9a3b24-5c2b-43ef-9d0b-52917268ead9)

5. #### Mostrar los datos de la Base de Datos en la tabla de gestión de Canchas
Se muestran los datos en una tabla ya establecida para los Usuarios administrador.
![image](https://github.com/user-attachments/assets/b146fbdd-763c-4d66-97b3-6cbbbb8b2018)
![image](https://github.com/user-attachments/assets/66064ec1-608c-4b3a-800e-274b22831b1f)

6. #### Eliminar un registro seleccionado
![image](https://github.com/user-attachments/assets/f94a39f3-2657-4786-bcc7-2c47ed0b11a7)

7. #### Actualizar un registro seleccionado
![image](https://github.com/user-attachments/assets/0ccb032b-a9c6-4e44-bd07-15614a5db3d4)

- ### Reservas
 En la clase Reservas se crearon los atributos: horario Reservado, input, btn, dia, mes, año; 
Getters y Setters
![image](https://github.com/user-attachments/assets/81a289c3-beee-44d5-bb10-7fa1ec938486)

#### Métodos personalizados
1. #### Número de reserva
Método que genera un número de reserva incrementando el número mayor de reserva registrado en la Base de Datos
![image](https://github.com/user-attachments/assets/596d16ea-2325-4216-8f9e-81934d77fbb5)

2. #### Mostrar los datos de la Base de Datos en la tabla de gestión de Reservas
Se muestran los datos en una tabla ya establecida para los Usuarios administrador.
![image](https://github.com/user-attachments/assets/9972def0-25c6-4c66-93d7-aae481e98ab5)

3. #### Seleccionar las opciones del JComboBox
Método booleano que verifica si se selecciono una opción para el número de jugadores
![image](https://github.com/user-attachments/assets/94b3d72c-b8ab-49bc-8f43-80e03b55a79c)

4. #### Tomar el valor del JComboBox
Método que extrae el texto que se encuentra en las opciones del JComboBox
![image](https://github.com/user-attachments/assets/7bab1cc1-409b-4bb3-9dea-6a0ff14e0f4e)

5. #### Verificar el horario ya reservado
![image](https://github.com/user-attachments/assets/ee606854-ac83-4d68-b34a-8bf364177fc1)

6. #### Verificar disponibilidad al momento de editar una cancha
![image](https://github.com/user-attachments/assets/8cafeaa4-0fad-4137-803a-6e75552ee005)

7. #### Mostrar los datos de la Base de Datos en la tabla de gestión de Reservas en el form de Dueño
Se muestran los datos en una tabla ya establecida para los Usuarios dueños.
![image](https://github.com/user-attachments/assets/8afbc6b3-37ba-4ab3-948f-0627e6903a7c)

8. #### Determinar que se haya seleccionado un registro de la tabla de Reservas
Se determina un método booleano que verifica que el administrador haya seleccionado una fila de la tabla.

9. #### Eliminar un registro seleccionado
![image](https://github.com/user-attachments/assets/9a770ef3-ea73-4f47-8a67-1c4416b7cf7c)

10. #### Actualizar registro seleccionado
![image](https://github.com/user-attachments/assets/87c9d3fa-a7b8-430c-806e-e4dcf767c8c7)

- ### Logeo
En la clase Logeo se crearon atributos estaticos para almacenar información una vez se inicia sesión
![image](https://github.com/user-attachments/assets/eda62e21-ba8d-4d73-b5b3-8cfa667730a5)
![image](https://github.com/user-attachments/assets/24276871-55a0-471c-b3ba-a17c04c212cc)

- ### ImageRenderer
Esta clase permite renderizar imágenes en una tabla de Java, permite que se muestre la imagen al definrla.
![image](https://github.com/user-attachments/assets/b26141de-0966-4695-abe3-a28fb88c1720)

3. ## Forms
