package com.wire.aves.server.application.login

import com.wire.aves.server.application.apiversion.VersionsResponse
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.login() = route("/${VersionsResponse.currentVersion.latest()}/user") {
    post({
        description = "Authenticate a user to obtain a cookie and first access token"
        request { body<SignInRequest>() }
        response { HttpStatusCode.Forbidden to { body<String> { } } }
    }) {
        // validate params
        // get user from db
        // checkpass, if error 403

        // build jwt with user id
        // generate access token
        // respoind with access token + cookie
    }
}


//@POST
//@ApiOperation(value = "Authenticate a user to obtain a cookie and first access token")
//@ApiResponses(value = {@ApiResponse(code = 403, message = "Wrong email or password")})
//public Response user(@ApiParam @Valid SignIn signIn) {
//    try {
//        UserDAO userDAO = jdbi.onDemand(UserDAO.class);
//
//        String email = signIn.email.toLowerCase().trim();
//
//        String hashed = userDAO.getHash(email);
//        if (hashed == null || !SCryptUtil.check(signIn.password, hashed)) {
//            return Response
//                .ok(new ErrorMessage("Authentication failed.", 403, "invalid-credentials"))
//            .status(403)
//                .build();
//        }
//
//        UUID userId = userDAO.getUserId(email);
//
//        long mills = TimeUnit.SECONDS.toMillis(config.tokenExpiration);
//        Date exp = new Date(new Date().getTime() + mills);
//
//        String token = Jwts.builder()
//            .setIssuer("https://aves.com")
//            .setSubject("" + userId)
//            .setExpiration(exp)
//            .signWith(Aves.getKey())
//            .compact();
//
//        AccessToken result = new AccessToken();
//        result.expiresIn = config.tokenExpiration;
//        result.accessToken = token;
//        result.tokenType = "Bearer";
//        result.user = userId;
//
//        return Response.
//        ok(result).
//        cookie(new NewCookie("zuid", result.accessToken)).
//        build();
//    } catch (Exception e) {
//        e.printStackTrace();
//        Logger.error("LoginResource.user : %s", e);
//        return Response
//            .ok(new ErrorMessage("Authentication failed.", 403, "invalid-credentials"))
//        .status(403)
//            .build();
//    }
//}
//}