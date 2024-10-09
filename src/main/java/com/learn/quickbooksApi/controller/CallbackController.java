package com.learn.quickbooksApi.controller;


import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;
import com.learn.quickbooksApi.client.OAuth2PlatformClientFactory;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author dderose
 *
 */
@Controller
public class CallbackController {

    @Autowired
    OAuth2PlatformClientFactory factory;

    private static final Logger logger = LoggerFactory.getLogger(CallbackController.class);

    /**
     *  This is the redirect handler you configure in your app on developer.intuit.com
     *  The Authorization code has a short lifetime.
     *  Hence Unless a user action is quick and mandatory, proceed to exchange the Authorization Code for
     *  BearerToken
     *
     * @param authCode
     * @param state
     * @param realmId
     * @param session
     * @return
     */
    @RequestMapping("/oauth2redirect")
    public String callBackFromOAuth(@RequestParam("code") String authCode, @RequestParam("state") String state, @RequestParam(value = "realmId", required = false) String realmId, HttpSession session) {
        logger.info("inside oauth2redirect of sample"  );
        try {
            String csrfToken = (String) session.getAttribute("csrfToken");
            if (csrfToken.equals(state)) {
                session.setAttribute("realmId", realmId);
                session.setAttribute("auth_code", authCode);
                logger.info("realmId: " +  realmId );
                logger.info("auth_code: " +  authCode );

                OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
                String redirectUri = factory.getPropertyValue("OAuth2AppRedirectUri");
                logger.info("redirectUri: " + redirectUri  );

                BearerTokenResponse bearerTokenResponse = client.retrieveBearerTokens(authCode, redirectUri);

                session.setAttribute("access_token", bearerTokenResponse.getAccessToken());
                session.setAttribute("refresh_token", bearerTokenResponse.getRefreshToken());

                // Update your Data store here with user's AccessToken and RefreshToken along with the realmId

                return "connected";
            }
            logger.info("csrf token mismatch " );
        } catch (OAuthException e) {
            logger.error("Exception in callback handler ", e);
        }
         return "error";
    }


}