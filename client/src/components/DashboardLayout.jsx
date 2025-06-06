import React from "react";
import { FaServer, FaUserShield, FaCog } from "react-icons/fa";

export default function DashboardLayout({ children }) {
    return (
        <div className="min-h-screen flex bg-gray-950 text-white font-sans">
            {/* Sidebar */}
            <aside className="w-64 bg-gray-900 border-r border-gray-800 p-6">
                <div className="text-xl font-bold mb-10 tracking-wide">⚡ Levi Monitor</div>
                <nav className="space-y-6 text-gray-400">
                    <NavItem icon={<FaServer />} label="Servers" />
                    <NavItem icon={<FaUserShield />} label="My Account" />
                    <NavItem icon={<FaCog />} label="Settings" />
                </nav>
            </aside>

            {/* Main */}
            <main className="flex-1 p-8 bg-gray-950 overflow-auto">{children}</main>
        </div>
    );
}

function NavItem({ icon, label }) {
    return (
        <div className="flex items-center gap-3 cursor-pointer hover:text-white transition">
            {icon}
            <span>{label}</span>
        </div>
    );
}
