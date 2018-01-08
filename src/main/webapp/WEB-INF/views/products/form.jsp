<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de produtos</title>
</head>
<body>
	<form:form action='${spring:mvcUrl("PC#save").build()}' method="post" modelAttribute="product" enctype="multipart/form-data">
		<div>
			<label for="titulo">Título</label>
			<form:input path="title" type="text" />
			<form:errors path="title" />
		</div>
		
		<div>
			<label for="description">Descrição</label>
			<form:textarea path="description" rows="10" cols="20" />
			<form:errors path="description" />
		</div>
		
		<div>
			<label for="pages">Número de páginas</label>
			<form:input path="pages" type="text" />
			<form:errors path="pages" />
		</div>
		
		<div>
			<label for="releaseDate">Data de Lançamento</label>
			<form:input type="date" path="releaseDate" />
			<form:errors path="releaseDate" />
		</div>
		
		<div>
			<label for="summary">Sumário do Livo</label>
			<input type="file" name="summary" />
			<form:errors path="summaryPath" />
		</div>
		
		<div>
			<input type="submit" value="Enviar" />
		</div>
		
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<input type="text" name="prices[${status.index}].value" id="price_${bookType}"/>
				<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/>
			</div>
		</c:forEach>
	</form:form>
</body>
</html>