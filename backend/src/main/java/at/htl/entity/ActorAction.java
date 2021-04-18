
package at.htl.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class ActorAction{

    @EmbeddedId
    private ActorActionKey actorActionKey;
    private double value;

    public ActorAction (ActorActionKey actorActionKey, double value){
        this.actorActionKey = actorActionKey;
        this.value = value;
    }

    public ActorAction (){

    }

    public ActorActionKey getActorActionKey() {
        return actorActionKey;
    }

    public void setActorActionKey(ActorActionKey actorActionKey) {
        this.actorActionKey = actorActionKey;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ActorAction{" +
                "actorActionKey=" + actorActionKey +
                ", value=" + value +
                '}';
    }

    @Embeddable
     public static class ActorActionKey implements Serializable {
        private Timestamp timestamp;
        @ManyToOne
        private Actor actor;

        public ActorActionKey(){

        }

        public ActorActionKey(Timestamp timestamp, Actor actor){
            this.timestamp = timestamp;
            this.actor = actor;

        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        public Actor getActor() {
            return actor;
        }

        public void setActor(Actor actor) {
            this.actor = actor;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ActorActionKey that = (ActorActionKey) o;
            return Objects.equals(timestamp, that.timestamp) && Objects.equals(actor, that.actor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, actor);
        }

        @Override
        public String toString() {
            return "ActorActionKey{" +
                    "timestamp=" + timestamp +
                    ", actor=" + actor +
                    '}';
        }
    }

}

