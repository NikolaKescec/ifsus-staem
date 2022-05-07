import React from "react";

import { Avatar, Button, Group, Header, Menu, Text } from "@mantine/core";
import { IconLogout, IconUser } from "@tabler/icons";

import { Link, useNavigate } from "react-router-dom";

import { useAuth0 } from "@auth0/auth0-react";

import LoginButton from "./LoginButton";

export default function MyNavbar() {
  return (
    <Header height={75} p="md">
      <Group position="apart">
        <Group spacing="sm">
          <NavbarLinks />
        </Group>
        <Group spacing="sm">
          <LoginButton />
          <UserProfile />
        </Group>
      </Group>
    </Header>
  );
}

function NavbarLinks() {
  const { isAuthenticated } = useAuth0();

  return (
    <>
      <Text component={Link} to="/" color="blue">
        Home
      </Text>
      {isAuthenticated && (
        <>
          <Text component={Link} to="/my-games" color="blue">
            My Games
          </Text>
        </>
      )}
    </>
  );
}

function UserProfile() {
  const { isAuthenticated, user, logout } = useAuth0();
  const navigate = useNavigate();

  if (!isAuthenticated) {
    return null;
  }

  return (
    <Menu
      control={
        <Button px={5}>
          <Avatar src={user?.picture} radius="xl" mr={5} size={30} />{" "}
          {user?.name}
        </Button>
      }
    >
      <Menu.Item icon={<IconUser />} onClick={() => navigate("/profile")}>
        Profile
      </Menu.Item>
      <Menu.Item
        icon={<IconLogout />}
        onClick={() => logout({ returnTo: window.location.origin })}
      >
        Log Out
      </Menu.Item>
    </Menu>
  );
}
