import classes from './MealsSummary.module.css';
import React from 'react';


const MealsSummary = () => {
  return (
    <section className={classes.summary}>
      <h2>Only Meat</h2>
      <p>
"Only Meat" was founded in 2008 by Danny Brand - a kibbutznik and farmer, who dreamed up the unique concept against all the advice of experts but with lots of love, professionalism and respect for meat.

      </p>
      <p>

Danny's craze, combined with the quality of the meats, made customers come from every corner of the country.      </p>
    </section>
  );
};

export default MealsSummary;
