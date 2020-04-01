/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @param <T> the task for the menu
 * @since 2019-05-20
 * This interface does some operation on the menus of the game.
 */
public interface Menu<T> extends Animation {
    /**
     * represent the main menu, add all the selection for this menu.
     * @param key - the key to press (for example 'h' for showing the high score table)
     * @param message - the message that write after the key (for example start game)
     * @param returnVal - the mission to do
     */
   void addSelection(String key, String message, T returnVal);
   /**
    *
    * @return T - the task to run
    */
   T getStatus();
   /**
    *
    * @return string to know the status
    */
   String getStatus1();
   /**
    * setter.
    * @param s - task
    */
   void setStatus(T s);
   /**
    * represent the sub menu, add all the selection for this menu.
    * @param key - the key to press
    * @param message - the message that write after the key
    * @param subMenu - the mission to do
    */
   void addSubMenu(String key, String message, Menu<T> subMenu);
   /**
    * represent the sub menu, add all the selection for this menu.
    * @param key - the key to press
    * @param message - the message that write after the key
    * @param subMenu - the mission to do
    */
   void addSubMenu(String key, String message, String subMenu);
}