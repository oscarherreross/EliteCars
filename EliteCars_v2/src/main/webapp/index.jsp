<web-app>
	<display-name>Archetype Created Web Application</display-name>

	<welcome-file-list>
		<welcome-file>index.jsp</welcome-file>
	</welcome-file-list>

	<servlet>
		<servlet-name>Hola</servlet-name>
		<servlet-class>prueba.Hola</servlet-class>
	</servlet>

	<servlet-mapping>
		<servlet-name>Hola</servlet-name>
		<url-pattern>/hola.html</url-pattern>
	</servlet-mapping>
	
</web-app>