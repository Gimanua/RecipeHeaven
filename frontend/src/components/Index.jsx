import React from 'react';
import NavigationBar from './NavigationBar';

export default function Index(props){
    return (
        <>
            <NavigationBar currentPage="home" />
            <h1 className="is-size-1 has-text-centered has-text-white">Recept Himlen</h1>
            <p className="has-text-centered has-text-white">
                Lorem ipsum, dolor sit amet consectetur adipisicing elit. 
                Error reiciendis deleniti voluptates repellat dicta vel iste quidem eius explicabo, placeat iusto delectus quam, aliquid consectetur? 
                Nesciunt voluptates nostrum praesentium itaque. Lorem ipsum dolor sit amet consectetur adipisicing elit. 
                Quia placeat facere quisquam iste ad ex ullam illum, excepturi, quaerat ipsam provident magnam minus enim. 
                Quibusdam repellat laboriosam quia quos culpa?Praesentium voluptatibus cum quas aliquid eum fugit hic molestias officiis quia id repudiandae, 
                eligendi nobis perferendis dolorum assumenda? Porro, error adipisci. Expedita laboriosam eveniet mollitia excepturi cum dignissimos omnis impedit?
            </p>
        </>
    );
}