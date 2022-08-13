import classes from './MealsSummary.module.css';
import React from 'react';


const MealsSummary = () => {
  return (
    <section className={classes.summary}>
      <h2>The Garage, A Real American Burger</h2>
      <p>
      The best place to eat a real american burger. Come to hang out with your friends and family for quality food!
      </p>
      <p>
And don't forget about the fries!     </p>
    </section>
  );
};

export default MealsSummary;
