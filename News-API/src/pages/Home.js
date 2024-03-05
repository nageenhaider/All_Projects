import React, { useEffect } from 'react'
import './page.css'
import  Displaynews  from './Displaynews';
import { useSelector, useDispatch } from 'react-redux';
import { fetchProducts } from '../redux/slice';


export const Home = () => {
  const dispatch = useDispatch();
  const data = useSelector((state) => state.products);
  console.log(data);
  useEffect(() => {
    dispatch(fetchProducts());
  }, [dispatch]);

  if (Array.isArray(data)) {
    return <div>Loading...</div>;
  }

  return (
    <div>
    <div className='home'>
    <div className='center'>
      <div className='home1'>
        Home
      </div>
      <div className='home2'>Welcome to my first News API Website!</div>
    </div>
  </div>
  
  <div className='headlines'>
    Headlines:
    <div>
    <ul>
    {data.articles.map((article) => {
              if (article.urlToImage) {
                return (
                  <li key={article.id}>
                    <Displaynews article={article} />
                    <br /><br />
                  </li>
                );
              }
              return null;
            })}

 </ul>

    </div>
  </div>



  </div>
  )
}
export default Home