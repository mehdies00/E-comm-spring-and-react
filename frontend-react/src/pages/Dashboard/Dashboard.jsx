import { useState, useEffect } from "react";
import { getAllArticles } from "../../services/articleService";
import "./Dashboard.css";

function Dashboard() {
  const [articles, setArticles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    getAllArticles()
      .then(setArticles)
      .catch(() => setError("Failed to load articles"))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="home-loading">Loading...</div>;
  if (error) return <div className="home-error">{error}</div>;

  return (
    <div className="home">
      <div className="home-header">
        <h2 className="home-title">Today's deals</h2>
      </div>

      <div className="home-grid">
        {articles.map((article) => (
          <div key={article.idArticle} className="product-card">
            <div className="product-img-wrap">
              <img
                src={article.imageUrl || "https://placehold.co/300x300?text=No+Image"}
                alt={article.name}
                className="product-img"
              />
            </div>
            <div className="product-info">
              <p className="product-name">{article.name}</p>
              <p className="product-brand">{article.brand}</p>
              <p className="product-price">${article.price?.toFixed(2)}</p>
              <p className="product-desc">{article.description}</p>
              <button className="product-btn">Add to cart</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Dashboard;