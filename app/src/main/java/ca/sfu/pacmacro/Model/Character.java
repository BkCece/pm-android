package ca.sfu.pacmacro.Model;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import Utils.BitmapUtil;
import ca.sfu.pacmacro.R;

/**
 * Data structure containing a character's information and position
 */
public class Character {
    private Marker marker;
    private CharacterType characterType;
    private CharacterState characterState;
    private int currentIcon;

    public enum CharacterType{
        PACMAN("Pacman", R.drawable.pacman, R.drawable.pacman_power_pill),
        INKY("Inky", R.drawable.inky, R.drawable.inky),
        BLINKY("Blinky", R.drawable.blinky, R.drawable.blinky),
        PINKY("Pinky", R.drawable.pinky, R.drawable.pinky),
        CLYDE("Clyde", R.drawable.clyde, R.drawable.clyde);

        String name;
        int iconId;
        int powerupIconId;

        CharacterType(String name, int iconId, int powerupIconId) {
            this.name = name;
            this.iconId = iconId;
            this.powerupIconId = powerupIconId;
        }

        @Override
        public String toString() {
            return name;
        }

        public int getIconId() {
            return iconId;
        }

        public int getPowerupIconId() {
            return powerupIconId;
        }
    }

    public enum CharacterState{
        UNINITIALIZED,
        READY,
        ACTIVE,
        CAPTURED,
        POWERUP
    }

    public Character(CharacterType characterType, CharacterState characterState, Marker marker) {
        this.characterType = characterType;
        this.characterState = characterState;
        this.marker = marker;
        this.currentIcon = characterType.getIconId();
    }

    public boolean isPacman() {
        return characterType == CharacterType.PACMAN;
    }

    public void updateLocation(LatLng characterLocation) {
        if (marker != null) {
            marker.setPosition(characterLocation);
        }
    }

    public void updateState(CharacterState characterState) {
        this.characterState = characterState;
    }

    @Override
    public String toString() {
        return characterType.toString();
    }

    public CharacterState getState() {
        return characterState;
    }

    public CharacterType getType() {
        return characterType;
    }

    public void updateMarkerVisibility(boolean isVisible) {
        this.marker.setVisible(isVisible);
    }

    public void setPowerupIcon(Context context) {
        // checking currentIcon before updating the icon ensures the icon is only actually updated when necessary
        if (currentIcon != characterType.getPowerupIconId()) {
            setCurrentIcon(context, characterType.getPowerupIconId());
        }
    }

    public void setDefaultIcon(Context context) {
        // checking currentIcon before updating the icon ensures the icon is only actually updated when necessary
        if (currentIcon != characterType.getIconId()) {
            setCurrentIcon(context, characterType.getIconId());
        }
    }

    private void setCurrentIcon(Context context, int drawableResourceId) {
        Bitmap icon = BitmapUtil.getBitmapFromDrawable(context, drawableResourceId);
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(icon));
        currentIcon = drawableResourceId;
    }
}
