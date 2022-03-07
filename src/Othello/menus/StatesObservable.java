package Othello.menus;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The class is an observer and is used to keep track of what state the program is in
 * so that PropertyChangeListeners can react to a change in State.
 * * The Class has a PropertyChangeSupport and a State that is first set to START.
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-06
 */
public class StatesObservable {

        private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
        private States state = States.START;

        /**
         * Adds a PropertyChangeListener to this class via PropertyChangeSupport.
         * @param startApp - A PropertyChangelistener that listens and reacts to a change in state.
        */
        public void addPropertyChangeListener(PropertyChangeListener startApp) {
            this.pcs.addPropertyChangeListener(startApp);
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            this.pcs.removePropertyChangeListener(listener);
        }

        public States getValue() {
            return this.state;
        }

        /**
        * Sets state to newValue and sends the old and new states as arguments
         * to firePropertyChange so that the relevant PropertyChangeListener can
         * react to the change of state.
         * @param newValue - A State that is set as new state for this.
        */
        public void setValue(States newValue) {
            States oldValue = this.state;
            this.state = newValue;
            this.pcs.firePropertyChange("state", oldValue, newValue);
        }
}
