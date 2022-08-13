import { useContext, useState } from 'react';
import React from 'react';


import Modal from '../UI/Modal';
import CartItem from './CartItem';
import classes from './Cart.module.css';
import CartContext from '../../store/cart-context';
import Checkout from './Checkout.js';
import { useParams } from 'react-router-dom';


const Cart = (props) => {
  const{id} = useParams();
  const [isCheckout, setIsCheckout] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [didSubmit, setDidSubmit] = useState(false);
  const cartCtx = useContext(CartContext);

  const totalAmount = `₪${cartCtx.totalAmount}`;
  const hasItems = cartCtx.items.length > 0;

  const cartItemRemoveHandler = (id) => {
    cartCtx.removeItem(id);
  };

  const cartItemAddHandler = (item) => {
    cartCtx.addItem({ ...item, amount: 1 });
  };

  const orderHandler = () => {
    setIsCheckout(true);
  };

  const submitOrderHandler = async (userData) => {
    setIsSubmitting(true);
    await fetch('http://localhost:8084/iob/activities',
    {
      method: 'POST',
      headers: {
    	 'Accept': 'application/json',
    	 'Content-Type': 'application/json',
  	  },
      body: JSON.stringify({
		activityId: null,
		type: "Order",
		instance:{
			instanceId:{
				domain:"2022B.Amit.Yehezkel",
				id: "d8c32bb5-3e28-4894-9bbb-fc5dc1cdace1"
			},
		},
		createdTimestamp: "2022",
		invokedBy:{
			userId:{
				domain:"2022B.Amit.Yehezkel",
				email: id
			},
		},
		activityAttributes:{
			userData: userData,
        	orderedItems: cartCtx.items
		}
        
      })
    });
    setIsSubmitting(false);
    setDidSubmit(true);
    cartCtx.clearCart();
  };

  const cartItems = (
    <ul className={classes['cart-items']}>
      {cartCtx.items.map((item) => (
        <CartItem
          key={item.id}
          name={item.name}
          amount={item.amount}
          price={item.price}
          onRemove={cartItemRemoveHandler.bind(null, item.id)}
          onAdd={cartItemAddHandler.bind(null, item)}
        />
      ))}
    </ul>
  );

  const modalActions =
    <div className={classes.actions}>
      <button className={classes['button--alt']} onClick={props.onClose}>
        Close
      </button>
      {hasItems && <button className={classes.button} onClick={orderHandler}>Order</button>}
    </div>

  const cartModalContent =
    <React.Fragment>
      {cartItems}
      <div className={classes.total}>
        <span>Total Amount</span>
        <span>{totalAmount}</span>
      </div>
      {isCheckout && <Checkout onConfirm={submitOrderHandler} onCancel={props.onClose} />}
      {!isCheckout && modalActions}
    </React.Fragment>

  const isSubmittingModalContent = <p class="final">Sending Order To Resturant...</p>;

  const didSubmitModalContent = (
    <React.Fragment>
      <p class="final">Successfully Sent The Order!</p>
      <div className={classes.actions}>
      <button className={classes.button} onClick={props.onClose}>
        Close
      </button>
    </div>
    </React.Fragment>
  );

  return (
    <Modal onClose={props.onClose}>
      {!isSubmitting && !didSubmit && cartModalContent}
      {isSubmitting && isSubmittingModalContent}
      {!isSubmitting && didSubmit && didSubmitModalContent}
    </Modal>
  );
};

export default Cart;
