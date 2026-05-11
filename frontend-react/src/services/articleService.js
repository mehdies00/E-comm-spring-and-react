const BASE_URL = "http://localhost:8080/api/articles";

export const getAllArticles = async () => {
  const token = localStorage.getItem("token");
  const res = await fetch(BASE_URL, {
    headers: {
      "Authorization": `Bearer ${token}`
    }
  });
  if (!res.ok) throw await res.json();
  return res.json();
};