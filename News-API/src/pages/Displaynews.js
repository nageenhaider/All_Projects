import React from 'react'
import './page.css'
 const Displaynews = ({article}) => {
  const truncatedUrl = `${article.url.slice(0, 30)}...`;
  return (
    <div>
        <div className='news-box'>
       <h3> {article.title}</h3> 
        <p>{article.description} </p>
        <p> Read More : 
        <a href={article.url} target="_blank" rel="noopener noreferrer">
            {truncatedUrl}
          </a>
      </p>

        <img src={article.urlToImage} alt={article.title} width="600" height="300"  />

        </div>
        
    </div>
  )
}

export default Displaynews;