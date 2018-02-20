<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
<meta charset="UTF-8">
<link href="/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<title>Смена издания книги</title>
</head>
<body>
	<div class="container">
		<form class="editor" action='<c:out value="/edit/${bookId}"/>' method="post" enctype="multipart/form-data">
			<input type="hidden" name="page" value='<c:out value="${page}"/>'/>
			<input type="hidden" name="bookId" value='<c:out value="${bookId}"/>'/>
			<div class="container">	
				<label>Автор:
					<c:choose>
						<c:when test="${bookId == 0}">
							<input name="author" maxlength="100" value='<c:out value="${author}"/>'/>
						</c:when>
						<c:otherwise>
							<span><c:out value="${author}"/></span>
						</c:otherwise>
					</c:choose>
				</label>
				<label>Название
					<input name="title" maxlength="100" value='<c:out value="${title}"/>'/>
				</label>
				<label>ISBN
					<input name="isbn" type="text" maxlength="20" value='<c:out value="${isbn}"/>'/>
				</label>
				<label>Год изд.
					<input name="printYear" type="text" maxlength="4" value='<c:out value="${printYear}"/>'/>
				</label>
				<label>Описание
					<textarea name="description" maxlength="255" rows=4><c:out value="${description}"/></textarea>
				</label>
			</div>
			
			<div class="container">
				<button>
					Сохранить
				</button>
				<a href='<c:out value="/page/${page}"/>'>Отмена</a>
			</div>
		</form>
	</div>
</body>
</html>
