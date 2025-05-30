package unoeste.fipp.mercadofipp.security.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unoeste.fipp.mercadofipp.security.JWTTokenProvider;

import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String token = req.getHeader("Authorization");
        String method = req.getMethod();
        String path = req.getRequestURI();

        if (method.equals("POST") && path.contains("/apis/usuario") || method.equals("GET")) {
            // Permitir acesso público a POST /apis/usuario (cadastro de usuário)
            chain.doFilter(request, response);
        } else if (method.equals("GET") && path.startsWith("/apis/anuncio")) {
            // Permitir GET público para /apis/anuncio/**
            chain.doFilter(request, response);
        } else if (token == null) {
            // Para os demais casos, precisa de token
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Não autorizado: token não encontrado");
        } else if (!JWTTokenProvider.verifyToken(token)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Não autorizado: token inválido");
        } else {
            Claims claims = JWTTokenProvider.getAllClaimsFromToken(token);
            int nivel = Integer.parseInt(claims.get("privilegio").toString());
            System.out.println(nivel);
            // Níveis do sistema:
            // 0 = adm
            // 1 = usuário comum (acessa poucos conteúdos e não consegue realizar exclusões)
            // 2 = publico (acessa quase nada)

            // TRATAR A CATEGORIA
            if (path.contains("/apis/categoria")) {
                // TRATAMENTO DA CATEGORIA
                if (method.equals("GET")) {
                    // GET categoria: adm e usuário comum
                    if (nivel <= 1) {
                        chain.doFilter(request, response);
                    } else {
                        forbidden(res);
                    }
                } else if (method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) {
                    // só adm pode POST, PUT, DELETE em categoria
                    if (nivel == 0) {
                        chain.doFilter(request, response);
                    } else {
                        forbidden(res);
                    }
                }
            }
            // TRATAR O USUÁRIO
            else if (path.contains("/apis/usuario")) {
                // TRATAMENTO PARA O USUÁRIO
                if (method.equals("GET")) {
                    // GET usuário (qualquer GET com /apis/user/**) - adm e usuário comum
                    if (nivel == 0 || nivel == 1) {
                        chain.doFilter(request, response);
                    } else {
                        forbidden(res);
                    }
                } else if (method.equals("DELETE")) {
                    // só adm pode deletar usuário
                    if (nivel == 0) {
                        chain.doFilter(request, response);
                    } else {
                        forbidden(res);
                    }
                } else if (method.equals("POST")) {
                    // POST usuário - liberado só para público (já tratado acima)
                    forbidden(res);
                } else if (method.equals("PUT")) {
                    // SÓ O ADM PODE EDITAR UM USUÁRIO
                    if (nivel == 0) {
                        chain.doFilter(request, response);
                    } else {
                        forbidden(res);
                    }
                }
            }
            // TRATAR O ANÚNCIO
            else if (path.contains("/apis/anuncio")) {
                // TRATAMENTO PARA O ANÚNCIO
                // GET público para get-anuncios liberado acima
                if (method.equals("GET")) {
                    // GET id, GET por usuário
                    if (path.matches("/apis/anuncio/get-por-usuario/\\d+") || path.matches("/apis/anuncio/\\d+")) {
                        if (nivel == 0 || nivel == 1) {
                            chain.doFilter(request, response);
                        } else {
                            forbidden(res);
                        }
                    }
                } else if (method.equals("POST")){
                    // POST add-pergunta e add-resposta
                    if (path.matches("/apis/anuncio/add-pergunta/\\d+/[^/]+") || path.matches("/apis/anuncio/add-resposta/\\d+/[^/]+")) {
                        if (nivel == 0 || nivel == 1) {
                            chain.doFilter(request, response);
                        } else {
                            forbidden(res);
                        }
                    } else if (path.contains("/apis/anuncio")){ // POST anúncio normal (ex: criação)
                        if (nivel == 0 || nivel == 1) {
                            chain.doFilter(request, response);
                        } else {
                            forbidden(res);
                        }
                    }
                } else if (method.equals("DELETE")){ // DELETE anúncio - só adm
                    if (nivel == 0) {
                        chain.doFilter(request, response);
                    } else {
                        forbidden(res);
                    }
                } else{
                    // Caso nenhum critério casou, nega acesso
                    forbidden(res);
                }
            }
        }
    }

    private void forbidden(HttpServletResponse res) throws IOException {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.getWriter().write("Acesso negado para seu nível de usuário");
    }
}