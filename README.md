Integrante: Matias Medina Martinez
**IMPORTANTE** verificar primero la contraseña de la base datos en el paquete de properties
Este proyecto simula los microservicios de un gimnasio, los microservicios independientes corresponden a:
	-ms-ejercicio: corresponde a un tipo de entrenamiento principal que se asignara al usuario
	-ms-entrenador: información de los entrenadores del gym
	-ms-usuario: información de los usuarios que utilizaran el gym
	-ms-nutrición: corresponde al plan de nutrición que el usuario esta siendo asignado
Cada microservicio hace las funcionalidades básicas de un micro servicio (post,put,get,delete)

Luego vienen los microservicios a los cuales les llegara la información por openfeign:
	-ms-horario: se le guardara la información correspondiente de los horarios que se darán las clases, guardara la información de los microservicios de usuarios y entrenadores que participaran en la clase
	-ms-asignación: aquí se guardara la información de los usuarios y los planes de entrenamiento, plan de nutrición
	-ms-pago: se guardara los pagos asociándolos a un usuario. 
Estos microservicios están hechos solamente para obtener información con método get y post para añadir. 
El microservicio de asignación se hizo sin método de try catch para errores para comprobaciones de que sucedería sin este método, mientras que los otros microservicios si lo tienen añadidos. El microservicio de horario se hizo con la implementación de @tomanytoMany, por lo que se hizo un modelo de "detalle horario" que sirve de intermediario para guardar la información de los usuarios y entrenadores como datos.

Adicionalmente se utilizo para comprobar la funcionalidad de los microservicios el servicio de "swagger-ui/index.html#".

La manera correcta de comprobar este proyecto es creando las databases de cada ms, ejecutar cada microservicio y luego poblar las tablas con datos. Todo esto esta en un archivo de texto SQL.
