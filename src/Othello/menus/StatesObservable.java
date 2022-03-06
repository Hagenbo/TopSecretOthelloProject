package Othello.menus;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * lägg till javadoc
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-06
 */
public class StatesObservable {

        private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
        private States state = States.START;

        public void addPropertyChangeListener(PropertyChangeListener startApp) {
            this.pcs.addPropertyChangeListener(startApp);
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            this.pcs.removePropertyChangeListener(listener);
        }

        public States getValue() {
            return this.state;
        }

        public void setValue(States newValue) {
            States oldValue = this.state;
            this.state = newValue;
            this.pcs.firePropertyChange("state", oldValue, newValue);
        }
}
