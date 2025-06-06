import React from "react";

export default function Login() {
    return (
        <div className="min-h-screen flex items-center justify-center bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900 text-white">
            <div className="w-full max-w-md p-8 space-y-6 bg-gray-950 rounded-2xl shadow-2xl border border-gray-800">
                <div className="text-center">
                    <h2 className="text-3xl font-bold tracking-wide">🔐 Login to Monitor</h2>
                    <p className="mt-2 text-sm text-gray-400">Secure access to your server dashboard</p>
                </div>

                <form className="space-y-4">
                    <div>
                        <label htmlFor="email" className="block mb-1 text-sm font-medium text-gray-300">
                            Email
                        </label>
                        <input
                            type="email"
                            id="email"
                            required
                            className="w-full p-2.5 bg-gray-800 border border-gray-700 rounded-lg text-sm focus:ring-indigo-500 focus:border-indigo-500"
                            placeholder="you@example.com"
                        />
                    </div>

                    <div>
                        <label htmlFor="password" className="block mb-1 text-sm font-medium text-gray-300">
                            Password
                        </label>
                        <input
                            type="password"
                            id="password"
                            required
                            className="w-full p-2.5 bg-gray-800 border border-gray-700 rounded-lg text-sm focus:ring-indigo-500 focus:border-indigo-500"
                            placeholder="••••••••"
                        />
                    </div>

                    <button
                        type="submit"
                        className="w-full py-2.5 bg-indigo-600 hover:bg-indigo-700 rounded-lg font-semibold transition duration-300"
                    >
                        Sign In
                    </button>
                </form>

                <div className="flex items-center justify-center mt-6">
                    <a
                        href="http://localhost:8080/oauth2/authorization/github"
                        className="flex items-center px-4 py-2 border border-gray-700 rounded-lg hover:bg-gray-800 transition"
                    >
                        <img src="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png" alt="GitHub" className="w-6 h-6 mr-2" />
                        <span className="text-sm font-medium">Sign in with GitHub</span>
                    </a>
                </div>
            </div>
        </div>
    );
}
