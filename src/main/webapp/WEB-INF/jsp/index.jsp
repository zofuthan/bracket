<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>The Bracket App</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <!-- Use Google Web Fonts -->
    <link href='' rel='stylesheet' type='text/css'>
    <script data-main="resources/js/init" src="resources/js/lib/base/require-min.js"></script>
    <script type="text/javascript">
        require.config({
            baseUrl: "/bracket/resources/js/",
            paths: {
                "html": "/bracket/resources/html"
            }
        });
    </script>
</head>
<body>
<c:if test="${not empty account.displayName}">
    <p>Welcome, <c:out value="${account.displayName}"/>!</p>
    <p><img src="${account.profileUrl}" /> </p>
    <a href="<c:url value="/signout" />">Sign Out</a>

    <ul>
        <li><a href="connect/twitter">Twitter</a> (Connected? <c:out value="${twitter_status}"/>)</li>
    </ul>
</c:if>

<form id="twitter_signin" action="/bracket/signin/twitter" method="post">
    <button type="submit" style="height: 26px; width: 150px;background: transparent url('resources/social/twitter/sign-in-with-twitter-d.png') no-repeat center top;"></button>
</form>


    <header>
        <h4>The Bracket App</h4>
    </header>


    <div id="main" role="main">
        <div id="bracketNode"></div>
    </div>


    <footer>
    </footer>


</body>
</html>