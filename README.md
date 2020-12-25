# Gestion-de-recursos-sociales
TP de Gestion de Recursos Sociales, de la materia Diseño de Sistemas de la UTN FRBA  
Compuesto por un modelo de objetos (release 3) + su persistencia (release 4) + una version web (release 5) + todo configurado para ser deployado en Heroku, utilizando el modelo MVC  
## Uso
Para usarlo de forma local, simplemente en los persistence.xml que están en src/main/resources/META-INF y en src/test/resources/META-INF, se van a ver 3 bloques de configuración, dos de ellos comentados. Uno es para usar una db local y otra es para mantener todo en memoria  
En caso de levantarlo local, se usa el puerto 9000 del localhost  
## Detalles
-El modelo de objetos tiene funciones no soportadas en la parte web tales como la carga de entidades o la bandeja de entrada de los egresos validados por usuario (en este caso, los egresos validos y sin validar lo pueden ver todos los uauarios sin discriminar)  
-[Version deployada](http://dds-grupo1-gesoc-prueba.herokuapp.com/)  
-Modelo de objetos desarrollado junto a [@jcastagno99](https://github.com/jcastagno99), [@TomasM99](https://github.com/TomasM99), [@julchat](https://github.com/julchat) y [@atta25](https://github.com/atta25)  
-Modelo de datos, mapeo y front-end desarrollado junto a [@julchat](https://github.com/julchat) y [@atta25](https://github.com/atta25)
