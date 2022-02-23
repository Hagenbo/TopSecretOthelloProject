package Othello.menus;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
