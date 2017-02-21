<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>


<form action="getImage" method="post" enctype="multipart/form-data">
	<input type="file" name="image" required="required" /><br /> <br />
	<input type="submit" />
</form>


<p />
<p />
<p />
<p />

<a href="displayImage?imageId=5">display image</a>