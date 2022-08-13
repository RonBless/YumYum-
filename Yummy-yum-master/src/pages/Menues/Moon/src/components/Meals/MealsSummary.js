import classes from './MealsSummary.module.css';
import React from 'react';


const MealsSummary = () => {
  return (
    <section className={classes.summary}>
      <h2>Moon Sushi Bar, The freshest sushi </h2>
      <p>
      If you have not yet gotten to know your taste buds the special Japanese flavors of MOON SUSHI BAR it's time for you to do so!
      </p>
      <p>
      MOON Restaurant was established over 20 years ago in Tel Aviv and since then we bring you a plate of special and innovative Japanese flavors from our exceptional sushi to fresh salads, traditional soups, tempura dishes and more - all inspired by Japanese cuisine.
      </p>
    </section>
  );
};

export default MealsSummary;
