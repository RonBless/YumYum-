import classes from './MealsSummary.module.css';
import React from 'react';


const MealsSummary = () => {
  return (
    <section className={classes.summary}>
      <h2>Benvenuto a Vivino!</h2>
      <p>
A magical place born for warm and close-knit families and good friends like you who love to hang out together, eat well, and most of all make happy, just like the Italians know.

      </p>
      <p>
Vivino brings you the flavors and aromas of traditional and modern Italian cuisine, with an appetizing design, an open kitchen with a traditional oven at its heart, cordial hospitality and a happy and light Italian atmosphere that is so appropriate to our character, the Israelis.

      </p>
    </section>
  );
};

export default MealsSummary;
