
<%@ page import="org.phpusr.simpleblog.Article" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'article.label', default: 'Article')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
        <script type="text/javascript">
            $(function() {
                getComments();
            });

            var userLink = "${createLink(controller: 'user', action: 'show')}";

            /** Вывод комментариев */
            function getComments() {
                $.get("${createLink(controller: 'comment', action: 'listAjax')}", {articleId: "${articleInstance?.id}"}, function (data) {
                    $("#comments").empty();
                    $.each(data, function(index, value) {
                        var li = $("<li/>", {class: 'fieldcontain'});
                        var user = $("<div/>", {
                            html: $("<a/>", { href: userLink, text: value.userName })
                        }).appendTo(li);
                        var comment = $("<div/>", {text: value.body}).appendTo(li);
                        $("#comments").prepend(li);
                    });
                });
            }

            /** Успешное добавление комментария */
            function addCommentSuccess() {
                $("#body").val('');
                getComments();
            }
        </script>
	</head>
	<body>
		<a href="#show-article" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <sec:ifAllGranted roles="ROLE_WRITER">
				    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </sec:ifAllGranted>
			</ul>
		</div>
		<div id="show-article" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list article">
			
				<g:if test="${articleInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="article.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${articleInstance?.user?.id}">${articleInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${articleInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="article.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${articleInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${articleInstance?.body}">
				<li class="fieldcontain">
					<span id="body-label" class="property-label"><g:message code="article.body.label" default="Body" /></span>
					
						<span class="property-value" aria-labelledby="body-label"><g:fieldValue bean="${articleInstance}" field="body"/></span>
					
				</li>
				</g:if>
			
			</ol>
            <sec:ifAuthor userId="${articleInstance?.userId}">
                <g:form url="[resource:articleInstance, action:'delete']" method="DELETE">
                    <fieldset class="buttons">
                        <g:link class="edit" action="edit" resource="${articleInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </sec:ifAuthor>
		</div>

        <div class="content">
            <h1><g:message code="article.commets.label" default="Comments" /></h1>
            <sec:ifLoggedIn>
                <div class="comment-form">
                    <g:formRemote name="formComment" url="[controller: 'comment', action: 'addAjax']" onSuccess="addCommentSuccess()">
                        <g:hiddenField name="article.id" value="${articleInstance?.id}" />
                        <g:textArea name="body" />
                        <g:submitButton name="submit" />
                    </g:formRemote>
                </div>
            </sec:ifLoggedIn>
            <ol id="comments" class="property-list">
            </ol>
        </div>
	</body>
</html>
