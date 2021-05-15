vcl 4.0;

import std;

backend default {
  .host = "api-catalog";
  .port = "6070";
}

sub vcl_recv {
    if (req.url ~ "^/api/" && req.method == "GET") {

        # Normalize the query parameters
        set req.url = std.querysort(req.url);

        # Strip the cookies
        unset req.http.cookie;

        return(hash);
    } else {
        return(pass);
    }
}

sub vcl_backend_response {
    if (bereq.url ~ "^/api/"){
        # time-to-live 1 hour
        set beresp.ttl = 3600s;
        unset beresp.http.set-cookie;
        unset beresp.http.Pragma;
        unset beresp.http.Expires;
    }

    # dont cache redirects and errors
    if (beresp.status >= 300) {
        set beresp.uncacheable = true;
        set beresp.ttl = 30s;
        return (deliver);
    }
    return (deliver);
}