<%@ page import="org.phpusr.simpleblog.Article" %>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="article.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" maxlength="100" required="" value="${articleInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'body', 'error')} required">
	<label for="body">
		<g:message code="article.body.label" default="Body" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="body" cols="40" rows="5" maxlength="2147483647" required="" value="${articleInstance?.body}"/>

</div>

