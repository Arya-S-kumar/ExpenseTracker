package com.example.extracker;

//This class represents the various methods to set and get the data in the adapter class.

public class Model {

    private boolean isSelected;
    private String animal;
    private String editTextValue;
    int imgs[] = {R.drawable.grocery, R.drawable.elect, R.drawable.water, R.drawable.food, R.drawable.edu, R.drawable.milk1, R.drawable.news, R.drawable.cable, R.drawable.recharg, R.drawable.shoping};


    public String getEditTextValue() {

        return editTextValue;
    }

    public void setEditTextValue(String editTextValue)
    {
        this.editTextValue = editTextValue;
    }

    public int setimage(int i){

        return imgs[i];
    }

    public String getAnimal() {

        return animal;
    }

    public void setAnimal(String animal) {

        this.animal = animal;
    }

    public boolean getSelected()
    {
        return isSelected;
    }

    public void setSelected(boolean selected) {

        isSelected = selected;
    }
}