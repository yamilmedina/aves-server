package com.wire.aves.server.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import com.wire.aves.server.domain.id.QualifiedId
import com.wire.aves.server.domain.user.User
import com.wire.aves.server.domain.user.UserRepository
import java.util.*

/**
 * remove later:
 * this has visibility on application layer, but user repository doesn't
 * this could be called service as well, but I think it´s better since we can group a user/business action here.
 */
interface PerformLoginUseCase {
    operator fun invoke(email: String, password: String): Either<Exception, Boolean>
}

internal class PerformLoginUseCaseImpl(private val userRepository: UserRepository) : PerformLoginUseCase {

    override fun invoke(email: String, password: String): Either<Exception, Boolean> = either {
        when {
            email == "roman@aves.com" && password == "Qwerty123" -> true
            else -> throw IllegalArgumentException("Invalid credentials")
        }
    }

    companion object {
        val inMemValidUser = User(
            qualifiedId = QualifiedId(UUID.fromString("71437131-ed9f-447f-be16-b88490323e6f"), "aves.com"),
            email = "roman@aves.com",
            handle = "roman.aves",
            phone = "49123123123",
            name = "roman bird",
            accent = 1
        )
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