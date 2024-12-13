package Listener;
/**
 *@author Nir Akay 207387770
 **/
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl a HitListener
     * */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl a HitListener
     * */
    void removeHitListener(HitListener hl);
}