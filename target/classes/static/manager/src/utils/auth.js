

const TOKEN_KEY = 'access_token';
const tokenApi = () => {
    return {
        getToken: () => {
            return localStorage.getItem(TOKEN_KEY);
        },
        setToken: (token) => {
            localStorage.setItem(TOKEN_KEY, token);
        },
        removeToken: () => {
            localStorage.removeItem(TOKEN_KEY);
        },
    }
}

export default tokenApi;
