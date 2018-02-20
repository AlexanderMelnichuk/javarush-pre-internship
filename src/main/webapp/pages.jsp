<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
<meta charset="UTF-8">
<link href="/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<title>Книги</title>
</head>
<body>
	<div class="container">
		<form class="search" action='<c:out value="/page/1"/>' method="get" enctype="multipart/form-data">	
			<label title="Можно использовать маску с %." style="margin-right:20px;">Поиск по названию: <input type="text" name="search" value='<c:out value="${search}"/>'/> <button><i class="fa fa-search"></i></button></label>
			<a href="/page/1">Сбросить фильтр</a>
		</form>
		
	</div>
	<table class="table table-bordered table-hover horizontal-align">
		<thead>
			<tr>
				<td width="10%">Автор</td>
				<td width="20%">Название</td>
				<td width="40%">Описание</td>
				<td width="5%">ISBN</td>
				<td width="5%">Год изд.</td>
				<td width="5%">Прочитана</td>
				<td width="5%"><a href="/create">Создать</a></td>
				<td width="5%">Удалить</td>
			</tr>
		</thead>
		<tbody>
			<c:url var="pageUrl" value="/page/${currentPage}" />
			<c:forEach var="book" items="${books}">
				<tr>
					<td><c:out value="${book.author}" /></td>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.description}" /></td>
					<td><c:out value="${book.isbn}" /></td>
					<td><c:out value="${book.printYear}" /></td>
					<td>
						<c:url var="markUrl" value="/markRead/${book.id}">
							<c:param name="page" value="${currentPage}"/>
							<c:choose>
								<c:when test="${search != null}">
									<c:param name="search" value="${search}"/>
								</c:when>
							</c:choose>
						</c:url>
						<c:choose>
							<c:when test="${book.readAlready}">
								<a href="${markUrl}"><i class="fa fa-check-circle-o"></i></a>
							</c:when>
							<c:otherwise>
								<a href="${markUrl}"><i class="fa fa-circle-thin"></i></a>
							</c:otherwise>
						</c:choose>
					</td>
					<td><a href='<c:out value="/edit/${book.id}?page=${currentPage}"/>' ">Редактировать</a></td>
					<td><a href='<c:out value="/delete/${book.id}?page=${currentPage}"/>' onclick="return confirm('Точно удалить?');">Удалить</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:url var="firstUrl" value="/page/1">
		<c:choose>
			<c:when test="${search != null}">
				<c:param name="search" value="${search}"/>
			</c:when>
		</c:choose>
	</c:url>
	<c:url var="lastUrl" value="/page/${page.totalPages}">
		<c:choose>
			<c:when test="${search != null}">
				<c:param name="search" value="${search}"/>
			</c:when>
		</c:choose>
	</c:url>
	<c:url var="prevUrl" value="/page/${currentPage - 1}">
		<c:choose>
			<c:when test="${search != null}">
				<c:param name="search" value="${search}"/>
			</c:when>
		</c:choose>
	</c:url>
	<c:url var="nextUrl" value="/page/${currentPage + 1}">
		<c:choose>
			<c:when test="${search != null}">
				<c:param name="search" value="${search}"/>
			</c:when>
		</c:choose>
	</c:url>

	<div class="pagination">
		<ul>
			<c:choose>
				<c:when test="${currentPage == 1}">
					<li class="disabled">&lt;&lt;</li>
					<li class="disabled">&lt;</li>
				</c:when>
				<c:otherwise>
					<li><a href="${firstUrl}">&lt;&lt;</a></li>
					<li><a href="${prevUrl}">&lt;</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<c:url var="pageUrl" value="/page/${i}">
					<c:choose>
						<c:when test="${search != null}">
							<c:param name="search" value="${search}"/>
						</c:when>
					</c:choose>
				</c:url>
				<c:choose>
					<c:when test="${i == currentPage}">
						<li class="active"><c:out value="${i}" /></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${currentPage >= page.totalPages}">
					<li class="disabled">&gt;</li>
					<li class="disabled">&gt;&gt;</li>
				</c:when>
				<c:otherwise>
					<li><a href="${nextUrl}">&gt;</a></li>
					<li><a href="${lastUrl}">&gt;&gt;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
</html>
