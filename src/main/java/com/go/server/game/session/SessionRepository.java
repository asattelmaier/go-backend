package com.go.server.game.session;

import com.go.server.game.session.model.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class SessionRepository {
    private final List<Session> sessions = new CopyOnWriteArrayList<>();

    public List<Session> getAll() {
        return sessions;
    }

    public void addSession(final Session session) {
        if (!hasSession(session)) {
            sessions.add(session);
        }
    }

    public void removeSession(final Session session) {
        sessions.remove(session);
    }

    public Session getSession(final String sessionId) {
        return sessions.stream()
                .filter(session -> session.has(sessionId))
                .findFirst()
                .orElse(Session.notFound(sessionId));
    }

    public Session updateSession(final Session session) {
        final int index = sessions.indexOf(session);

        if (!hasSession(session)) {
            return Session.notFound(session.getId());
        }

        sessions.set(index, session);

        return session;
    }

    private Boolean hasSession(final Session session) {
        return sessions.contains(session);
    }
}
