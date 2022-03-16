

# bootcamp-reactive-subscribers
## Database connection

'spring.r2dbc.password=B00tcamp2022'

## Ejecutar un contenedor cambiando variables de entorno
`docker run -d --name subscribers-prd -p 9093:8080 -e "spring_profiles_active=prd" bootcamp-subscribers:0.0.1`
`docker run -d --name subscribers-prd -p 9093:8080 -e "spring_profiles_active=qas" -e "application_urlApiBlog=http://172.17.0.3:8080/blogs" bootcamp-subscribers:0.0.1`

## Ejecutar comandos dentro de un contenedor
### Mostrar variables de entorno
`docker exec -it subscribers-dev printenv`
### Abrir una sesión de bash
`docker exec -it subscribers-dev /bin/bash`

