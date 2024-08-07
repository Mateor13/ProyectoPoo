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

2. ## Forms

1. ### Menu Inicio
Se muestra una imagen y se da la opción de ingresar a el login o cerrar la aplicación
![image](https://github.com/user-attachments/assets/4a8b6ce3-48b8-423c-9963-af34f38ea19d)

   
2. ### Login
Se verifican las credenciales para iniciar como los diferentes Usuarios.
Se muestran los botones para verificar credenciales, registrarrse o volver al menu de inicio.
![image](https://github.com/user-attachments/assets/965eaa42-ba15-4e5f-bd89-6303e5844901)
![image](https://github.com/user-attachments/assets/ce84ffa4-72c2-478c-9c5e-e847849a0047)
![image](https://github.com/user-attachments/assets/a98ca695-66b5-4200-ac91-b19a28b13ab3)
![image](https://github.com/user-attachments/assets/7713ccec-53bb-473a-b62e-0c9edabcb938)

3. ### Registro
Se ingresan lo diferentes datos necesarios para registrarse: nombre, apellido, email, telefono, clave, cedula, confirmación de clave, fecha (Se desglosa un calendario para elegir la fecha).
En el caso del calendario, se requirio de una librería con un componente para la palette, y de esta forma poder utilizar este campo. También se creó una función createUIComponents() para poder inicializar este recurso.
![image](https://github.com/user-attachments/assets/ceb2ba61-61c7-46aa-ad2d-cf21e3a965ba)
![image](https://github.com/user-attachments/assets/5f4138e7-8c85-44c5-9dec-5eb56961d144)
![image](https://github.com/user-attachments/assets/fea2556f-4a0c-4898-87e5-465f8b42f55a)
![image](https://github.com/user-attachments/assets/e764ce41-1d15-4453-b877-4b71808ee684)
![image](https://github.com/user-attachments/assets/5dcff87d-db4e-4b49-ac2c-97fff05535a1)

4. ### Inicio
Se presenta una tabla con las canchas registradas, se debe seleccionar una cancha y aplastar el botón de "Elegir fecha de reserva" para redirigirse al form fecha. O aplastar el botón de cerrar sesión para volver al login. De no elegir una cancha dará error 
![image](https://github.com/user-attachments/assets/7f48f3db-93bf-45db-b00f-1cc750eae42c)
![image](https://github.com/user-attachments/assets/8d1ef6cb-08cc-45e3-b0dd-983a6655674f)

  
5. ### Fecha
Se mostrará un calendario para poder seleccionar la fecha, siempre tiene que ser posterior a la actual, por tanto, no se puede elegir el día actual. Una vez elegida la fecha se redireccionará al form Reservar. O se puede regresar al Inicio con el botón "Regresar".
![image](https://github.com/user-attachments/assets/fff76170-6612-461d-a615-3e207a725f81)
![image](https://github.com/user-attachments/assets/b5a2663b-2ffe-4ffd-a12e-ac98421e8e4d)

6. ### Reservar
Se mostrará las reservas realizadas para esa cancha y en esa fecha, además, se mostrará el botón para elegir el horario. Si no se elige saldrá un error y de elegirse un horario ya reservado, se buscará en la Base de Datos, y se mostrará el error. De no haber inconvenientes saldrá un mensaje de exito y se moostrará en la tabla. Cuenta con los botones de reservar y regresar, para volver al form de Inicio.
![image](https://github.com/user-attachments/assets/7b314c91-9036-43bb-b69e-d73225932948)
![image](https://github.com/user-attachments/assets/15688180-0400-4150-892c-170e120c12b1)
![image](https://github.com/user-attachments/assets/ef5ecd02-1723-4b4f-8a67-28fa13afe865)

7. ### Admin
Se mostrarán 4 botones para redirigir a los forms de gestión de Usuarios, canchas y reservas, además del botón de cerrar sesión para volver al login.  
![image](https://github.com/user-attachments/assets/aad41eee-2425-47aa-b184-2f88b1cb1ec4)


8. ### Gestión Canchas
Se muestra la tabla con las cancha sin imagen y los botones de ingresar nueva cancha, editar, eliminar y Regresa al menú administrador. Para eliminar y editar de debe seleccionar una cancha de la tabla. De no hacerlo se mostrará un error. Cada botón redireccionará a un form diferente 
![image](https://github.com/user-attachments/assets/d4142c9a-c2b3-4989-b834-87dcd8de18a0)
![image](https://github.com/user-attachments/assets/546ec8f1-035e-4475-acef-79e4f6f0285f)

9. ### Gestión Usuarios
Se muestra la tabla con los usuarios jugadores o clientes y los botones de ingresar nueva cancha, editar, eliminar y Regresa al menú administrador. Para eliminar y editar de debe seleccionar un usuario de la tabla. De no hacerlo se mostrará un error. Cada botón redireccionará a un form diferente 
![image](https://github.com/user-attachments/assets/29a88b5f-3de6-4128-9b1e-6309ddee42d6)
![image](https://github.com/user-attachments/assets/78aeda4d-e07f-4bce-afe4-2fce38b9cde3)

10. ### Gestión Reservas
Se muestra la tabla con las reservas y los botones de editar, eliminar y Regresa al menú administrador. Para eliminar y editar de debe seleccionar una reserva de la tabla. De no hacerlo se mostrará un error. Cada botón redireccionará a un form diferente 
![image](https://github.com/user-attachments/assets/ea30a1d6-69b4-494b-a72e-036395a76efc)
![image](https://github.com/user-attachments/assets/fdacd48d-88e3-487c-b120-f0dbb51aaa62)

11. ### AdIngrUsu
Es un form similar al Registro.
![image](https://github.com/user-attachments/assets/e1ca562f-6f56-4769-98dd-6b2b017f950e)
![image](https://github.com/user-attachments/assets/bdb38cc7-1836-4ede-9624-ba580a14dff3)
![image](https://github.com/user-attachments/assets/c2fc6f77-6312-4e21-9f9c-0e0f17d4b89b)
![image](https://github.com/user-attachments/assets/430f7e8b-841c-458e-b98d-08ce1c6f0309)

12. ### AdIngrCan
En este form se agregaran los atributos de las cancha como lo son el nombre, dirección, número de cancha, número de jugadores y una imagen. Todos los campos deben ser llenados y cumplir con ciertos requisitos.
![image](https://github.com/user-attachments/assets/2b9b8782-3737-4f20-b61c-efeae0508274)
![image](https://github.com/user-attachments/assets/f8fe786c-4f00-4d3e-9def-e08d332c479a)
![image](https://github.com/user-attachments/assets/3f1fbc80-3102-4c90-a666-8aab5884f0c3)

13. ### ElimCancha, ElimUsuario y ElimReservas
En este form se asegura de que en realidad se va a borrar el atributo seleccionado. Con dos botones de "Aceptar" y "Cancelar". Con un texto determinado por si es cancha, jugador o reserva.
![image](https://github.com/user-attachments/assets/759f2c57-4589-4332-b545-d23a5719a3c1)

14. ### EditarCanchas
Se muestran los botones con los campos a cambiar, según se selecciona se irán actualizando los campos. No se puede actualizar el número de cancha ni la imagen. Se verifican los datos antes de actualizar.
![image](https://github.com/user-attachments/assets/437bb1dc-facd-4179-b67f-695669f4ed13)
![image](https://github.com/user-attachments/assets/665c9163-aba5-4478-9dda-b9411292a1a8)
![image](https://github.com/user-attachments/assets/8dac33e0-f423-43af-991d-a26f33bbe601)
![image](https://github.com/user-attachments/assets/7319c337-cc04-4030-9dcc-9103dfbe8405)
![image](https://github.com/user-attachments/assets/98b94d0d-ce67-4aaf-adb8-4e397cb07aeb)

15. ### EditarJugadores
Se muestran los botones con los campos a cambiar, según se selecciona se irán actualizando los campos. No se puede actualizar la cédula. Se verifican los datos antes de actualizar. En el caso de la contraseña, se pedirá la contraseña actual del Usuario y la confirmación de la nueva contraseña
![image](https://github.com/user-attachments/assets/a05508e9-a53e-4b57-8f83-4b2a08430a4f)
![image](https://github.com/user-attachments/assets/46803075-b583-4271-9eb4-056d229f7910)
![image](https://github.com/user-attachments/assets/60abe297-191a-4291-93f5-60a76328f966)
![image](https://github.com/user-attachments/assets/927e432f-7854-4ee7-b770-cc0ebf45dd0f)
![image](https://github.com/user-attachments/assets/ac1498a1-e9b7-4019-9f9b-d10db3ba7cc8)
![image](https://github.com/user-attachments/assets/6efa1044-099c-4a79-9751-4ff2073b3a7e)
![image](https://github.com/user-attachments/assets/801cdd29-2d0f-435d-936e-7a4e82fc2634)
![image](https://github.com/user-attachments/assets/0c0aa3db-3ff6-4b75-90ff-0a899bd36f64)
![image](https://github.com/user-attachments/assets/27d9be4d-6e15-4ee3-b86a-00571b4ca86b)
![image](https://github.com/user-attachments/assets/96b3a4c7-39e6-4dea-b5bb-c441f0cafb02)
![image](https://github.com/user-attachments/assets/3ed316f8-da1c-48c3-a076-f706aca9badd)

16. ### EditarReservas
Se muestran los botones con los campos a cambiar, según se selecciona se irán actualizando los campos. No se puede actualizar el número de reserva ni la cancha seleccionada. Se verifican los datos antes de actualizar.
![image](https://github.com/user-attachments/assets/4d227d5b-9780-4c1e-8357-8ed18f3160b4)
![image](https://github.com/user-attachments/assets/99b360af-8360-4be2-814e-84bf4d397900)
![image](https://github.com/user-attachments/assets/e717d6f2-f8d6-47e5-b89f-b4de288e6917)
![image](https://github.com/user-attachments/assets/6e8015ec-c142-4381-a0f3-f3350631f9b8)
![image](https://github.com/user-attachments/assets/902cf0ff-92ff-40e3-b824-3b3cc1e230a6)
![image](https://github.com/user-attachments/assets/28a3332b-fd2f-47e4-a195-95b64287ad24)

17. ### Dueño
Se muestra un boton para visializar la tabla de reservas, lo que nos llevará al form BuscarC y cerrar sesión.
![image](https://github.com/user-attachments/assets/f265609b-3cde-474d-a85c-2ade38bd3329)


18. ### BuscarC
Aparece un botón de ver reservas realizadas y se mostrará la tabla con las reservas y el botón para volver al menú de dueño.
![image](https://github.com/user-attachments/assets/fe1ccbc0-19cf-4922-9552-cc1333392ebc)

